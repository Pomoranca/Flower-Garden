package com.coffeetime.simplenetworkrequest.ui.landing

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.coffeetime.simplenetworkrequest.MainActivity
import com.coffeetime.simplenetworkrequest.R
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
        val isLoggedin = SharedPrefManager.getInstance(requireContext()).isLoggedIn

        if (isLoggedin) {
            startMainActivity()

        }

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

        return binding.root
    }

    fun startMainActivity() {
        startActivity(Intent(activity, MainActivity::class.java))
        viewModel.doneNavigating()
    }

}