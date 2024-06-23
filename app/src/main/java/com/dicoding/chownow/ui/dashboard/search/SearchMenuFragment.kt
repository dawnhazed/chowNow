package com.dicoding.chownow.ui.dashboard.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.chownow.R
import com.dicoding.chownow.data.remote.response.MenuItem
import com.dicoding.chownow.data.remote.response.MenuResponse
import com.dicoding.chownow.data.remote.retrofit.ApiConfig
import com.dicoding.chownow.databinding.FragmentSearchMenuBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchMenuFragment : Fragment() {

    private var _binding: FragmentSearchMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = binding.rvMenuSearch
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        // Example: fetch search results based on the query
        val query = "mie"
        fetchSearchResults(query)
    }

    private fun fetchSearchResults(query: String) {
        val apiService = ApiConfig.getApiService()
        apiService.searchMenu(query).enqueue(object : Callback<MenuResponse> {
            override fun onResponse(call: Call<MenuResponse>, response: Response<MenuResponse>) {
                if (response.isSuccessful) {
                    val searchResults = response.body()?.menu ?: listOf()
                    val adapter = SearchMenuAdapter(searchResults)
                    binding.rvMenuSearch.adapter = adapter
                }
            }

            override fun onFailure(call: Call<MenuResponse>, t: Throwable) {
                // Handle failure
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
