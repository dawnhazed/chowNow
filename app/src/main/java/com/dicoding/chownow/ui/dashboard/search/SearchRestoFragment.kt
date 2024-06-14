package com.dicoding.chownow.ui.dashboard.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.chownow.R
import com.dicoding.chownow.data.model.ListResto
import com.dicoding.chownow.databinding.FragmentSearchRestoBinding

class SearchRestoFragment : Fragment() {

    private var _binding: FragmentSearchRestoBinding? = null
    private val binding get() = _binding!!

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

        // Siapkan data Resto
        val restaurants = listOf(
            ListResto(R.drawable.breakfast, "Nama Restoran 1", 4.0f),
            ListResto(R.drawable.breakfast, "Nama Restoran 2", 4.5f),
            ListResto(R.drawable.breakfast, "Nama Restoran 3", 4.9f)
        )

        // Atur adapter untuk Resto
        val adapter = SearchRestoAdapter(restaurants)
        recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}