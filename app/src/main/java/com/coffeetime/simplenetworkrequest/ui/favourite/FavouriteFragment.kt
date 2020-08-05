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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


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

        viewModel.favoriteFlower.observe(viewLifecycleOwner, Observer {
            if(it != null) {
                for (i in it.favFlowers.indices) {
                    binding.textFavorite.append(it.favFlowers[i].flower.name)

                }
            }
        })


        return binding.root

    }
}