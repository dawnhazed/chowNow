package com.dicoding.chownow.ui.dashboard.resto.detail

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.dicoding.chownow.R
import com.dicoding.chownow.databinding.FragmentDetailMenuBinding

class DetailMenuFragment : Fragment() {
    private var _binding: FragmentDetailMenuBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailMenuViewModel by viewModels()
    companion object {
        private const val ARG_PARAM = "index"
        fun newInstance(index: Int) = DetailMenuFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_PARAM, index)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { val index = it.getInt(ARG_PARAM) }
        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDetailMenuBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}