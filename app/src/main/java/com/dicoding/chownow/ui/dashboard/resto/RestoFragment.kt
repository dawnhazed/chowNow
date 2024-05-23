package com.dicoding.chownow.ui.dashboard.resto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dicoding.chownow.databinding.FragmentRestoBinding

class RestoFragment : Fragment() {

    private var _binding: FragmentRestoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val restoViewModel =
            ViewModelProvider(this).get(RestoViewModel::class.java)

        _binding = FragmentRestoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textResto
        restoViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}