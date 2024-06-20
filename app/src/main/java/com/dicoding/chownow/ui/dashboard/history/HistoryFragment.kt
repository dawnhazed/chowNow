package com.dicoding.chownow.ui.dashboard.history

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
import com.dicoding.chownow.data.model.HistoryOrder
import com.dicoding.chownow.databinding.FragmentHistoryBinding
import java.util.Date

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()

        val recyclerViewOrderSaatIni: RecyclerView = binding.rvPesananSaatIni
        recyclerViewOrderSaatIni.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewOrderSaatIni.isNestedScrollingEnabled = false

        val recyclerViewOrderSudahSelesai: RecyclerView = binding.rvPesananSelesai
        recyclerViewOrderSudahSelesai.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewOrderSudahSelesai.isNestedScrollingEnabled = false

        // Siapkan data HistoryOrder saat ini
        val historyOrderListSaatIni = listOf(
            HistoryOrder(R.drawable.food_example, "Rumah Makan Nusantara", 3, Date(), false)
            // Tambahkan lebih banyak data sesuai kebutuhan
        )

        // Siapkan data HistoryOrder sudah selesai
        val historyOrderListSudahSelesai = listOf(
            HistoryOrder(R.drawable.jco, "JCo", 3, Date(), true),
            HistoryOrder(R.drawable.burger_king, "Burger King", 2, Date(), true),
            HistoryOrder(R.drawable.kfc, "KFC", 4, Date(), true),
            HistoryOrder(R.drawable.mie_gacoan, "Mie Gacoan", 3, Date(), true)
            // Tambahkan lebih banyak data sesuai kebutuhan
        )

        // Atur adapter untuk HistoryOrder saat ini
        val adapterHistorySaatIni = HistoryOrderAdapter(historyOrderListSaatIni)
        recyclerViewOrderSaatIni.adapter = adapterHistorySaatIni

        // Atur adapter untuk HistoryOrder sudah selesai
        val adapterHistorySudahSelesai = HistoryOrderAdapter(historyOrderListSudahSelesai)
        recyclerViewOrderSudahSelesai.adapter = adapterHistorySudahSelesai
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