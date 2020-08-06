package com.coffeetime.simplenetworkrequest.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.coffeetime.simplenetworkrequest.databinding.FavoriteGridViewItemBinding
import com.coffeetime.simplenetworkrequest.network.FavFlowerX

class FavoriteGridAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<FavFlowerX, FavoriteGridAdapter.FavoriteFlowerViewHolder>(FLOWER_COMPARATOR) {

    class FavoriteFlowerViewHolder(private var binding: FavoriteGridViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(flower: FavFlowerX) {
            binding.flowerX = flower
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteFlowerViewHolder {
        return FavoriteFlowerViewHolder(FavoriteGridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: FavoriteFlowerViewHolder, position: Int) {
        val flower = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(flower!!)
        }
        holder.bind(flower)
    }

    class OnClickListener(val clickListener: (flower: FavFlowerX) -> Unit) {
        fun onClick(flower: FavFlowerX) = clickListener(flower)
    }

    companion object {
        private val FLOWER_COMPARATOR = object : DiffUtil.ItemCallback<FavFlowerX>() {
            override fun areItemsTheSame(oldItem: FavFlowerX, newItem: FavFlowerX): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: FavFlowerX, newItem: FavFlowerX): Boolean {
                return oldItem.id == newItem.id
            }
        }

    }

}