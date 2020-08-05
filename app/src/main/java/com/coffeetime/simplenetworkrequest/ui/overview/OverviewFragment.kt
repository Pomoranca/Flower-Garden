package com.coffeetime.simplenetworkrequest.ui.overview

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isNotEmpty
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.coffeetime.simplenetworkrequest.databinding.FragmentOverviewBinding
import com.coffeetime.simplenetworkrequest.util.SharedPrefManager
import com.coffeetime.simplenetworkrequest.util.SharedPrefManager.Companion.CURRENT_PAGE


class OverviewFragment : Fragment() {

    private val viewModel: OverviewViewModel by lazy {
        ViewModelProviders.of(this).get(OverviewViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentOverviewBinding.inflate(inflater)

        val CHILD_COUNT = 5

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



        binding.photosGrid.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {

                if (binding.photosGrid.isNotEmpty() && !binding.photosGrid.canScrollVertically(1) && binding.photosGrid.childCount > CHILD_COUNT) {
                    Log.i("SCROLLED", "SCROLLED")
                    CURRENT_PAGE++
                    viewModel.addFlowers()
                    (binding.photosGrid.adapter as PhotoGridAdapter).notifyDataSetChanged()

                }
            }
        })

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