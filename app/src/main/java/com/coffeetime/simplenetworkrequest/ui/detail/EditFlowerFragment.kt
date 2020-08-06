package com.coffeetime.simplenetworkrequest.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.coffeetime.simplenetworkrequest.databinding.FragmentEditFlowerBinding


class EditFlowerFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val application = requireNotNull(activity).application
        val binding = FragmentEditFlowerBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val flower = EditFlowerFragmentArgs.fromBundle(
            requireArguments()
        ).selectedFlower
        val viewModelFactory =
            EditFlowerViewModelFactory(
                flower,
                application
            )

        binding.viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(EditFlowerViewModel::class.java)



        return binding.root
    }


}