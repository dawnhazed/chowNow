package com.dicoding.chownow.ui.dashboard.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.chownow.R
import com.dicoding.chownow.data.model.ListResto
import com.dicoding.chownow.data.pref.UserPreference
import com.dicoding.chownow.data.pref.dataStore
import com.dicoding.chownow.data.remote.response.RecommendResponse
import com.dicoding.chownow.data.remote.response.RecommendationItem
import com.dicoding.chownow.data.remote.response.ReviewResponseItem
import com.dicoding.chownow.data.remote.retrofit.ApiConfig
import com.dicoding.chownow.databinding.FragmentHomeBinding
import com.dicoding.chownow.ui.dashboard.UlasanAdapter
import com.dicoding.chownow.ui.dashboard.geolocation.LocationActivity
import com.dicoding.chownow.ui.dashboard.shared.SharedViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var reviewAdapter: UlasanAdapter
    private var reviewResponse: List<ReviewResponseItem?> = listOf()
    private val viewModel: HomeViewModel by activityViewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()

    private lateinit var pref: UserPreference

    companion object {
        const val REQUEST_LOCATION = 1
        var USER_ID = 1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("DashboardActivity", "Inflating layout")

        // Initialize UserPreference
        pref = UserPreference.getInstance(requireContext().dataStore)

        // Initialize reviewAdapter
        reviewAdapter = UlasanAdapter(reviewResponse)

        setupRecyclerView()

        // Collect user session data
        viewLifecycleOwner.lifecycleScope.launch {
            pref.getSession().collect { user ->
                if (user.isLogin) {
                    getRecommendationResto(user.userId.toInt())
                }
            }
        }

        binding.btnEditLokasi.setOnClickListener { intentLocation() }
        viewModel.selectedLocation.observe(viewLifecycleOwner) { location ->
            Log.d("HomeFragment", "Observed location change: $location")
            binding.tvLokasiSaatIniValue.text = location
        }
    }

    private fun setupRecyclerView() {
        // Setup RecyclerView for recommendations
        val recyclerViewResto: RecyclerView? = view?.findViewById(R.id.rv_resto_rekomendasi)
        recyclerViewResto?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        // Setup RecyclerView for reviews
        val recyclerViewUlasan: RecyclerView? = view?.findViewById(R.id.rv_ulasan)
        recyclerViewUlasan?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerViewUlasan?.adapter = reviewAdapter

        getReviews()
    }

    private fun intentLocation() {
        val intent = Intent(context, LocationActivity::class.java)
        startActivityForResult(intent, REQUEST_LOCATION)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_LOCATION && resultCode == Activity.RESULT_OK) {
            val location = data?.getStringExtra("selected_location")
            location?.let {
                viewModel.setSelectedLocation(it)
                // Kirim lokasi ke SearchFragment
                val bundle = Bundle()
                bundle.putString("location", it)
                findNavController().navigate(R.id.action_homeFragment_to_searchRestoFragment, bundle)
            }
        }
    }

    private fun getRecommendationResto(userId: Int) {
        ApiConfig.getApiService().recommend(userId).enqueue(object : Callback<RecommendResponse> {
            override fun onResponse(call: Call<RecommendResponse>, response: Response<RecommendResponse>) {
                if (response.isSuccessful) {
                    val recommendations = response.body()?.recommendation.orEmpty()
                    Log.d("HomeFragment", "API response received: $recommendations")

                    // Update UI with recommendation data
                    val restaurants = recommendations.map {
                        ListResto(
                            imgResto = it?.imageUrl ?: R.drawable.breakfast, // Replace R.drawable.breakfast with actual default image resource
                            namaResto = it?.name ?: "Unknown",
                            ratingScale = it?.rating?.toFloat() ?: 0.0f
                        )
                    }

                    // Save to SharedViewModel
                    sharedViewModel.setRestaurants(restaurants)
                } else {
                    Log.e("HomeFragment", "Failed to get recommendations: ${response.errorBody()?.string()}")
                    Toast.makeText(context, "Failed to get recommendations", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<RecommendResponse>, t: Throwable) {
                Log.e("HomeFragment", "API call failed: ${t.message}")
                Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }


    private fun getReviews() {
        ApiConfig.getApiService().reviews().enqueue(object : Callback<List<ReviewResponseItem>> {
            override fun onResponse(call: Call<List<ReviewResponseItem>>, response: Response<List<ReviewResponseItem>>) {
                if (response.isSuccessful) {
                    val reviews = response.body()?.take(10) ?: listOf()
                    Log.d("HomeFragment", "API response received: $reviews")
                    if (reviews.isNotEmpty()) {
                        reviewResponse = reviews
                        reviewAdapter.updateData(reviews)
                    } else {
                        Log.d("HomeFragment", "No reviews found")
                    }
                } else {
                    Log.e("HomeFragment", "Failed to get reviews: ${response.errorBody()?.string()}")
                    Toast.makeText(context, "Failed to get reviews", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<ReviewResponseItem>>, t: Throwable) {
                Log.e("HomeFragment", "API call failed: ${t.message}")
                Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
