package com.coffeetime.simplenetworkrequest.ui.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.coffeetime.simplenetworkrequest.databinding.FragmentOverviewBinding
import com.coffeetime.simplenetworkrequest.util.SharedPrefManager


class OverviewFragment : Fragment() {

    private val viewModel: OverviewViewModel by lazy {
        ViewModelProviders.of(this).get(OverviewViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentOverviewBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel



        binding.logOutButton.setOnClickListener {
            SharedPrefManager.getInstance(requireContext()).clear()
            requireActivity().onBackPressed()
        }


        viewModel.userInfo.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                binding.helloTextView.text = "Welcome ${it.firstName.capitalize()}"
            }
        })

        binding.photosGrid.adapter =
            PhotoGridAdapter(
                PhotoGridAdapter.OnClickListener {
                    viewModel.displayFlowerDetails(it)
                })

//        binding.photosGrid.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//
//            }
//        })

        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                this.findNavController().navigate(
                    OverviewFragmentDirections.actionNavHostFragmentToEditFlowerFragment(
                        it
                    )
                )
                viewModel.displayFlowerDetailsComplete()
            }
        })
//        setHasOptionsMenu(true)
        return binding.root
    }


}