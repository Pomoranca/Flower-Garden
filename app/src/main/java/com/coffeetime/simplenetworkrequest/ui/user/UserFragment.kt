package com.coffeetime.simplenetworkrequest.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.coffeetime.simplenetworkrequest.databinding.FragmentUserBinding


class UserFragment : Fragment() {

    val viewModel by lazy {
        ViewModelProviders.of(this).get(UserViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding = FragmentUserBinding.inflate(inflater)


        viewModel.user.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                binding.username.text = "First name ${it.firstName}"
                binding.lastName.text = "last name ${it.lastName}"

            }
        })


        return binding.root
    }


}