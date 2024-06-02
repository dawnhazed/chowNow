package com.dicoding.chownow.ui.dashboard.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.chownow.R
import com.dicoding.chownow.data.model.Resto
import com.dicoding.chownow.databinding.FragmentHomeBinding
import com.dicoding.chownow.ui.dashboard.RestoAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val recyclerView: RecyclerView = binding.recyclerView
//        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
//        recyclerView.adapter = RestoAdapter(listOf("Item 1", "Item 2", "Item3"))

//        val recyclerView: RecyclerView = findViewById(R.id.rv_resto_rekomendasi)
        val recyclerView: RecyclerView = view.findViewById(R.id.rv_resto_rekomendasi)


        // Atur LayoutManager ke Horizontal
//        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager

        // Siapkan data
        val restaurants = listOf(
            Resto(R.drawable.breakfast, "Nama Restoran 1", 4.5f),
            Resto(R.drawable.breakfast, "Nama Restoran 2", 4.5f),
            Resto(R.drawable.breakfast, "Nama Restoran 3", 4.5f),
            Resto(R.drawable.breakfast, "Nama Restoran 4", 4.5f),
            Resto(R.drawable.breakfast, "Nama Restoran 5", 4.5f),
            Resto(R.drawable.breakfast, "Nama Restoran 6", 4.5f),
            Resto(R.drawable.breakfast, "Nama Restoran 7", 4.5f),
            Resto(R.drawable.breakfast, "Nama Restoran 8", 4.5f),
            Resto(R.drawable.breakfast, "Nama Restoran 9", 4.5f),
            Resto(R.drawable.breakfast, "Nama Restoran 10", 4.5f)
            // Tambahkan lebih banyak restoran sesuai kebutuhan
        )

//        val data = (1..20).map { "Item $it" }

        // Atur adapter
        val adapter = RestoAdapter(restaurants)
        recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        val homeViewModel =
//            ViewModelProvider(this).get(HomeViewModel::class.java)
//
//        _binding = FragmentHomeBinding.inflate(inflater, container, false)
//        val root: View = binding.root
//
//        val textView: TextView = binding.tvJudulHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
//        return root
//    }
}