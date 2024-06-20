package com.dicoding.chownow.ui.dashboard.home

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.chownow.R
import com.dicoding.chownow.data.model.Resto
import com.dicoding.chownow.data.model.Ulasan
import com.dicoding.chownow.data.remote.response.ReviewResponse
import com.dicoding.chownow.data.remote.response.ReviewResponseItem
import com.dicoding.chownow.data.remote.retrofit.ApiConfig
import com.dicoding.chownow.databinding.FragmentHomeBinding
import com.dicoding.chownow.ui.dashboard.RestoRekomendasiAdapter
import com.dicoding.chownow.ui.dashboard.UlasanAdapter
import com.dicoding.chownow.ui.dashboard.geolocation.LocationActivity
import com.dicoding.chownow.ui.dashboard.resto.RestoFragment
import com.dicoding.chownow.ui.loginregister.register.RegisterActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var reviewAdapter: UlasanAdapter

    private var _binding: FragmentHomeBinding? = null
    private var reviewResponse: List<ReviewResponseItem?> = listOf()
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by activityViewModels()

    companion object {
        const val REQUEST_LOCATION = 1
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
        setupView()

        reviewAdapter = UlasanAdapter(reviewResponse)

        // Setup RecyclerView Rekomendasi Resto
        val recyclerViewResto: RecyclerView = view.findViewById(R.id.rv_resto_rekomendasi)
        recyclerViewResto.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        // Setup RecyclerView Ulasan
        val recyclerViewUlasan: RecyclerView = view.findViewById(R.id.rv_ulasan)
        recyclerViewUlasan.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerViewUlasan.adapter = reviewAdapter

        // Siapkan data Resto
        val restaurants = listOf(
            Resto(R.drawable.breakfast, "Nama Restoran 1", 35, 4.5f),
            Resto(R.drawable.breakfast, "Nama Restoran 2", 50, 4.7f),
            Resto(R.drawable.breakfast, "Nama Restoran 3", 75, 4.9f)
        )

        // Siapkan data Ulasan
        /* val reviews = listOf(
            Ulasan(
                R.drawable.ic_launcher_background,
                "Macilor Bang Jaka",
                "Macilor 2 porsi",
                "Abangnya seru, gak cuma cilornya yg enak. Nanti datang lagi dah.",
                "Ratna Dewi",
                R.drawable.ic_launcher_background
            ),
            Ulasan(
                R.drawable.ic_launcher_background,
                "Macilor Bang Jaka",
                "Macilor 2 porsi",
                "Abangnya seru, gak cuma cilornya yg enak. Nanti datang lagi dah.",
                "Ratna Dewi",
                R.drawable.ic_launcher_background
            )
        ) */

        // Atur adapter untuk Resto
        val adapterResto = RestoRekomendasiAdapter(restaurants)
        recyclerViewResto.adapter = adapterResto

        // Atur adapter untuk Ulasan

        getReviews()

        binding.btnEditLokasi.setOnClickListener { intentLocation() }

        // Update lokasi ketika viewModel berubah
        viewModel.selectedLocation.observe(viewLifecycleOwner) { location ->
            Log.d("HomeFragment", "Observed location change: $location")
            binding.tvLokasiSaatIniValue.text = location
        }
    }

    private fun setupView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            activity?.window?.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            activity?.window?.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        (activity as? AppCompatActivity)?.supportActionBar?.hide()
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
            }
        }
    }

    private fun getReviews() {
        ApiConfig.getApiService().reviews().enqueue(object : Callback<List<ReviewResponseItem>> {
            override fun onResponse(call: Call<List<ReviewResponseItem>>, response: Response<List<ReviewResponseItem>>) {
                if (response.isSuccessful) {
                    val reviews = response.body() ?: listOf()
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
