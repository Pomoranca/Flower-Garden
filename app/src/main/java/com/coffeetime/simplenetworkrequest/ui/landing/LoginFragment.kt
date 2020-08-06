package com.coffeetime.simplenetworkrequest.ui.landing

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.coffeetime.simplenetworkrequest.MainActivity
import com.coffeetime.simplenetworkrequest.databinding.FragmentLoginBinding
import com.coffeetime.simplenetworkrequest.util.SharedPrefManager


class LoginFragment : Fragment() {
    val viewModel by lazy {
        ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentLoginBinding.inflate(inflater)
        binding.viewModel = viewModel

        binding.registerButton.setOnClickListener {
            it.findNavController()
                .navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragmentFragment())
        }

        viewModel.navigateToMainActivity.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                startMainActivity()
            }
        })
        binding.loginButton.setOnClickListener {
            viewModel.loginUser(
                binding.emailTextView.text.toString(),
                binding.passwordTextView.text.toString()
            )
        }

        if(SharedPrefManager.getInstance(requireContext()).isLoggedIn()){
            requireActivity().finish()
        }

        return binding.root
    }

    private fun startMainActivity() {
        startActivity(Intent(activity, MainActivity::class.java))
        viewModel.doneNavigating()
    }

}