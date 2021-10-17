package com.trendster.oyelabs.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.trendster.oyelabs.databinding.OfferRowLayoutBinding
import com.trendster.oyelabs.ui.data.model.Item

class OfferAdapter : ListAdapter<Item, OfferAdapter.OfferViewHolder>(COMPARATOR) {

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Item>() {
            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.title == newItem.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        return OfferViewHolder(
            OfferRowLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class OfferViewHolder(private val binding: OfferRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(offerItem: Item) = binding.apply {
            imgItem.load(offerItem.img)
            txtItemName.text = offerItem.title
            txtItemDesc.text = offerItem.description
            txtPrice.text = offerItem.price.toString() + " $"
        }
    }
}
