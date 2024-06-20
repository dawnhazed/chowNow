package com.dicoding.chownow.ui.dashboard.profile

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dicoding.chownow.databinding.FragmentProfileBinding
import com.dicoding.chownow.ui.loginregister.WelcomeActivity

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    // Property ini hanya valid antara onCreateView dan onDestroyView.
    private val binding get() = _binding!!

    companion object{
        private const val EMAIL_KEY = "EMAIL"

        fun newInstance(email: String): ProfileFragment {
            val fragment = ProfileFragment()
            val bundle = Bundle().apply {
                putString(EMAIL_KEY, email)
            }
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()

        val email = getEmailFromPreferences()
        Log.d("ProfileFragment", "Email from SharedPreferences: $email")
        binding.edittextEmail.text = email

        binding.buttonLogout.setOnClickListener {
            val sharedPreferences = requireContext().getSharedPreferences("prefs", AppCompatActivity.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()

            val intent = Intent(requireContext(), WelcomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            requireActivity().finish()

            Toast.makeText(requireContext(), "Logout berhasil", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getEmailFromPreferences(): String? {
        val sharedPreferences = requireContext().getSharedPreferences("prefs", AppCompatActivity.MODE_PRIVATE)
        return sharedPreferences.getString(EMAIL_KEY, null)
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