package com.dicoding.chownow.ui.dashboard.resto.detail

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.chownow.R
import com.dicoding.chownow.data.model.DetailReview
import com.dicoding.chownow.databinding.FragmentDetailReviewBinding
import java.util.*
import java.util.concurrent.TimeUnit // Import ini yang benar

class DetailReviewFragment : Fragment() {
    private var _binding: FragmentDetailReviewBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailReviewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailReviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup RecyclerView Review
        val recyclerViewReview: RecyclerView = binding.rvDetailReview
        recyclerViewReview.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        // Siapkan data Ulasan dengan tanggal custom
        val review = listOf(
            DetailReview(
                R.drawable.breakfast,
                "Nama Restoran A",
                "Burger 2 porsi",
                "Mantap banget laaa",
                R.drawable.ic_launcher_background,
                Date(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(3)) // 3 hari lalu
            ),
            DetailReview(
                R.drawable.breakfast,
                "Nama Restoran A",
                "Pizza 1 porsi",
                "Enak sekali!",
                R.drawable.ic_launcher_background,
                Date(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(17)) // 17 hari lalu
            )
        )

        // Atur adapter untuk Review
        val adapterReview = DetailReviewAdapter(review)
        recyclerViewReview.adapter = adapterReview
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}