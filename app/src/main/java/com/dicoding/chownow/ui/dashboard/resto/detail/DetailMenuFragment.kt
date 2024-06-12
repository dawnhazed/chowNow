package com.dicoding.chownow.ui.dashboard.resto.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.chownow.R
import com.dicoding.chownow.data.model.FoodMenu
import com.dicoding.chownow.databinding.FragmentDetailMenuBinding

class DetailMenuFragment : Fragment() {
    private var _binding: FragmentDetailMenuBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailMenuViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerViewMenu: RecyclerView = binding.rvDetailMenu
        recyclerViewMenu.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        // Siapkan data Menu
        val menu = listOf(
            FoodMenu(R.drawable.breakfast, "Paket Nasi Ayam Bakar", 25000, 1),
            FoodMenu(R.drawable.breakfast, "Paket Nasi Ayam Bakar", 25000, 1),
            FoodMenu(R.drawable.breakfast, "Paket Nasi Ayam Bakar", 25000, 1)
        )

        // Atur adapter untuk menu
        val adapter = DetailMenuAdapter(menu)
        recyclerViewMenu.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
