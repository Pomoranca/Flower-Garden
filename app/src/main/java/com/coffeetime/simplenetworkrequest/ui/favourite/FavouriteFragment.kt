package com.coffeetime.simplenetworkrequest.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.coffeetime.simplenetworkrequest.databinding.FragmentFavoriteBinding


class FavouriteFragment : Fragment() {

    val viewModel by lazy {
        ViewModelProviders.of(this).get(FavoriteViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentFavoriteBinding.inflate(inflater)

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