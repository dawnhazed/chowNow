package com.dicoding.chownow.ui.dashboard.home

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.chownow.R
import com.dicoding.chownow.data.model.Resto
import com.dicoding.chownow.data.model.Ulasan
import com.dicoding.chownow.databinding.FragmentHomeBinding
import com.dicoding.chownow.ui.dashboard.RestoRekomendasiAdapter
import com.dicoding.chownow.ui.dashboard.UlasanAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()

        // Setup RecyclerView Rekomendasi Resto
        val recyclerViewResto: RecyclerView = view.findViewById(R.id.rv_resto_rekomendasi)
        recyclerViewResto.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        // Setup RecyclerView Ulasan
        val recyclerViewUlasan: RecyclerView = view.findViewById(R.id.rv_ulasan)
        recyclerViewUlasan.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        // Siapkan data Resto
        val restaurants = listOf(
            Resto(R.drawable.breakfast, "Nama Restoran 1", 35, 4.5f),
            Resto(R.drawable.breakfast, "Nama Restoran 2", 50, 4.7f),
            Resto(R.drawable.breakfast, "Nama Restoran 3", 75, 4.9f)
            // Tambahkan lebih banyak restoran sesuai kebutuhan
        )

        // Siapkan data Ulasan
        val reviews = listOf(
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
            // Tambahkan lebih banyak ulasan sesuai kebutuhan
        )

        // Atur adapter untuk Resto
        val adapterResto = RestoRekomendasiAdapter(restaurants)
        recyclerViewResto.adapter = adapterResto

        // Atur adapter untuk Ulasan
        val adapterUlasan = UlasanAdapter(reviews)
        recyclerViewUlasan.adapter = adapterUlasan
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