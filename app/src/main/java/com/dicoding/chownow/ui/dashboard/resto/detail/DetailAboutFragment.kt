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
import com.dicoding.chownow.databinding.FragmentDetailAboutBinding

class DetailAboutFragment : Fragment() {
    private var _binding: FragmentDetailAboutBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailAboutViewModel by viewModels()
    companion object {
        private const val ARG_PARAM = "index"
        fun newInstance(index: Int) = DetailAboutFragment().apply {
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
        val detailAboutViewModel = ViewModelProvider(this).get(DetailAboutViewModel::class.java)

        _binding = FragmentDetailAboutBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textTitleLeft1.text = getString(R.string.didirikan)
        binding.textAboutLeft1.text = getString(R.string.keterangan)
        binding.textTitleLeft2.text = getString(R.string.jenis)
        binding.textAboutLeft2.text = getString(R.string.keterangan)
        binding.textTitleRight1.text = getString(R.string.sejak)
        binding.textAboutRight1.text = getString(R.string.keterangan)
        binding.textTitleRight2.text = getString(R.string.dijual)
        binding.textAboutRight2.text = getString(R.string.keterangan)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}