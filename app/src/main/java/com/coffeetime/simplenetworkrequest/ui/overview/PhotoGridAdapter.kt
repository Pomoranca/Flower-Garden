/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.coffeetime.simplenetworkrequest.ui.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.coffeetime.simplenetworkrequest.databinding.GridViewItemBinding
import com.coffeetime.simplenetworkrequest.network.Flower


/**
 * This class implements a [RecyclerView] [ListAdapter] which uses Data Binding to present [List]
 * data, including computing diffs between lists.
 */
class PhotoGridAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Flower, PhotoGridAdapter.FlowerViewHolder>(
        FLOWER_COMPARATOR
    ) {

    /**
     * The FlowerViewHolder constructor takes the binding variable from the associated
     * GridViewItem, which nicely gives it access to the full [Flower] information.
     */
    class FlowerViewHolder(private var binding: GridViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(flower: Flower) {
            binding.flower = flower
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of [Flower]
     * has been updated.
     */

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FlowerViewHolder {
        return FlowerViewHolder(
            GridViewItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: FlowerViewHolder, position: Int) {
        val flower = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(flower!!)
        }
        holder.bind(flower!!)
    }

    /**
     * Custom listener that handles clicks on [RecyclerView] items.  Passes the [Flower]
     * associated with the current item to the [onClick] function.
     * @param clickListener lambda that will be called with the current [Flower]
     */
    class OnClickListener(val clickListener: (flower: Flower) -> Unit) {
        fun onClick(flower: Flower) = clickListener(flower)
    }

    companion object {
        private val FLOWER_COMPARATOR = object : DiffUtil.ItemCallback<Flower>() {
            override fun areItemsTheSame(oldItem: Flower, newItem: Flower): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Flower, newItem: Flower): Boolean {
                return oldItem.id == newItem.id
            }
        }

    }
}
