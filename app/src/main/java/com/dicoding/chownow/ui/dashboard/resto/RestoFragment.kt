package com.dicoding.chownow.ui.dashboard.resto

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.chownow.R
import com.dicoding.chownow.databinding.FragmentRestoBinding
import com.dicoding.chownow.ui.dashboard.ListRestoAdapter
import com.dicoding.chownow.ui.dashboard.shared.SharedViewModel

class RestoFragment : Fragment() {

    private var _binding: FragmentRestoBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
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

        // Observe data restoran dari SharedViewModel
        sharedViewModel.restaurants.observe(viewLifecycleOwner, Observer { restaurants ->
            // Atur adapter untuk Resto
            val adapter = ListRestoAdapter(restaurants)
            recyclerView.adapter = adapter
        })

        binding.backIcon.setOnClickListener { backIntent() }
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

    private fun backIntent() {
        findNavController().navigateUp()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
