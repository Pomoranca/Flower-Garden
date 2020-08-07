package com.coffeetime.simplenetworkrequest.ui.favourite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.coffeetime.simplenetworkrequest.databinding.FavoriteGridViewItemBinding
import com.coffeetime.simplenetworkrequest.network.models.FavoriteFlower

class FavoriteGridAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<FavoriteFlower, FavoriteGridAdapter.FavoriteFlowerViewHolder>(FLOWER_COMPARATOR) {

    class FavoriteFlowerViewHolder(private var binding: FavoriteGridViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(flower: FavoriteFlower) {
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

    class OnClickListener(val clickListener: (flower: FavoriteFlower) -> Unit) {
        fun onClick(flower: FavoriteFlower) = clickListener(flower)
    }

    companion object {
        private val FLOWER_COMPARATOR = object : DiffUtil.ItemCallback<FavoriteFlower>() {
            override fun areItemsTheSame(oldItem: FavoriteFlower, newItem: FavoriteFlower): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: FavoriteFlower, newItem: FavoriteFlower): Boolean {
                return oldItem.id == newItem.id
            }
        }

    }

}