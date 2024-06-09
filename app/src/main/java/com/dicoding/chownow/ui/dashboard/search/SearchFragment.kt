package com.dicoding.chownow.ui.dashboard.search

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.chownow.R
import com.dicoding.chownow.data.model.ListResto
import com.dicoding.chownow.databinding.FragmentSearchBinding
import com.dicoding.chownow.ui.dashboard.HistorySearchAdapter
import com.dicoding.chownow.ui.dashboard.ListRestoAdapter


class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()

        val recyclerView: RecyclerView = binding.rvRestoSearch
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        // Siapkan data Resto
        val restaurants = listOf(
            //ListResto(R.drawable.breakfast, "Nama Restoran 1", 2.32f, 7, 4.5f)
            ListResto(R.drawable.breakfast, "Nama Restoran 1", 2.32f, 3, 4.0f),
            ListResto(R.drawable.breakfast, "Nama Restoran 2", 2.41f, 4, 4.5f),
            ListResto(R.drawable.breakfast, "Nama Restoran 3", 3.02f, 6, 4.9f),
            // Tambahkan lebih banyak restoran sesuai kebutuhan
        )

        // Atur adapter untuk Resto
        val adapter = HistorySearchAdapter(restaurants)
        recyclerView.adapter = adapter
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}