package com.coffeetime.simplenetworkrequest.ui.favourite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.coffeetime.simplenetworkrequest.R
import com.coffeetime.simplenetworkrequest.databinding.FragmentFavouriteBinding


class FavouriteFragment : Fragment() {

    val viewModel by lazy {
        ViewModelProviders.of(this).get(FavoriteViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentFavouriteBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        binding.favoritePhotosGrid.adapter =
            FavoriteGridAdapter(
                FavoriteGridAdapter.OnClickListener {
//                    viewModel.displayFlowerDetails(it)
                })


        return binding.root

    }
}