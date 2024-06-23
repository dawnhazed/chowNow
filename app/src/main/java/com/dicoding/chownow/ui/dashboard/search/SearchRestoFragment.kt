package com.dicoding.chownow.ui.dashboard.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.chownow.data.local.entity.RestaurantEntity
import com.dicoding.chownow.data.remote.response.SearchResponse
import com.dicoding.chownow.data.remote.retrofit.ApiConfig
import com.dicoding.chownow.databinding.FragmentSearchRestoBinding
import com.dicoding.chownow.ui.dashboard.RestaurantViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchRestoFragment : Fragment() {

    private var _binding: FragmentSearchRestoBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RestaurantViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchRestoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = binding.rvRestoSearch
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        // Ambil lokasi dari bundle
        val location = arguments?.getString("location") ?: return
        fetchSearchResults(location)

        // Observe data dari ViewModel
        viewModel.restaurants.observe(viewLifecycleOwner) { restaurants ->
            val adapter = SearchRestoAdapter(restaurants)
            recyclerView.adapter = adapter
        }
    }

    private fun fetchSearchResults(location: String) {
        val apiService = ApiConfig.getApiService()
        apiService.search(location).enqueue(object : Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                if (response.isSuccessful) {
                    val searchResults = response.body()?.restaurants ?: listOf()
                    val restaurantEntities = searchResults.map {
                        RestaurantEntity(
                            id = it.id ?: 0,
                            name = it.name ?: "",
                            location = it.location ?: "",
                            rating = it.rating?.toFloat() ?: 0f,
                            restoImage = it.restoImage ?: ""
                        )
                    }
                    viewModel.insertAll(restaurantEntities)
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                // Handle failure
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
