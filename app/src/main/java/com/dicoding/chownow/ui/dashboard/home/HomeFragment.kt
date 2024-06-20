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
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.chownow.R
import com.dicoding.chownow.data.model.Resto
import com.dicoding.chownow.data.model.Ulasan
import com.dicoding.chownow.databinding.FragmentHomeBinding
import com.dicoding.chownow.ui.dashboard.RestoRekomendasiAdapter
import com.dicoding.chownow.ui.dashboard.UlasanAdapter
import com.dicoding.chownow.ui.dashboard.geolocation.LocationActivity
import com.dicoding.chownow.ui.dashboard.resto.RestoFragment
import com.dicoding.chownow.ui.loginregister.register.RegisterActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
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
        )

        // Atur adapter untuk Resto
        val adapterResto = RestoRekomendasiAdapter(restaurants)
        recyclerViewResto.adapter = adapterResto

        // Atur adapter untuk Ulasan
        val adapterUlasan = UlasanAdapter(reviews)
        recyclerViewUlasan.adapter = adapterUlasan

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
