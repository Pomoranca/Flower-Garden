package com.coffeetime.simplenetworkrequest.ui.landing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.coffeetime.simplenetworkrequest.databinding.FragmentRegisterBinding


class RegisterFragmentFragment : Fragment() {
    val viewModel by lazy {
        ViewModelProviders.of(this).get(RegisterViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentRegisterBinding.inflate(inflater)
        viewModel.navigateToLandingFragment.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                this.findNavController()
                    .navigate(RegisterFragmentFragmentDirections.actionRegisterFragmentFragmentToLoginFragment())
                Toast.makeText(activity, "User created", Toast.LENGTH_SHORT).show()
                viewModel.doneNavigating()
            }
        })
        ViewModelProviders.of(this)

        binding.registerButton.setOnClickListener {

            viewModel.registerUser(
                binding.emailTextView.text.toString(),
                binding.passwordTextView.text.toString(),
                binding.firstNameEditText.text.toString(),
                binding.lastNameEditText.text.toString(),
                binding.dobEditText.text.toString()
            )
        }

        return binding.root
    }


}