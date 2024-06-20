package com.dicoding.chownow.ui.dashboard.resto

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.chownow.R
import com.dicoding.chownow.data.model.ListResto
import com.dicoding.chownow.databinding.FragmentRestoBinding
import com.dicoding.chownow.ui.dashboard.ListRestoAdapter
import com.dicoding.chownow.ui.dashboard.geolocation.LocationActivity

class RestoFragment : Fragment() {

    private var _binding: FragmentRestoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRestoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()

        val recyclerView: RecyclerView = binding.rvListResto
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        // Siapkan data Resto
        val restaurants = listOf(
            //ListResto(R.drawable.breakfast, "Nama Restoran 1", 2.32f, 7, 4.5f)
            ListResto(R.drawable.breakfast, "Nama Restoran 1", 4.0f),
            ListResto(R.drawable.breakfast, "Nama Restoran 2", 4.5f),
            ListResto(R.drawable.breakfast, "Nama Restoran 3", 4.9f),
            // Tambahkan lebih banyak restoran sesuai kebutuhan
        )

        // Atur adapter untuk Resto
        val adapter = ListRestoAdapter(restaurants)
        recyclerView.adapter = adapter

        binding.backIcon.setOnClickListener{ backIntent() }
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

    private fun backIntent(){
        findNavController().navigate(R.id.action_homeFragment_to_restoFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}