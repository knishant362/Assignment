package com.trendster.oyelabs.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.trendster.oyelabs.databinding.TopOfferRowLayoutBinding
import com.trendster.oyelabs.ui.data.model.TopOffer

class TopOfferAdapter : ListAdapter<TopOffer, TopOfferAdapter.TopOfferViewHolder>(COMPARATOR) {

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<TopOffer>() {
            override fun areContentsTheSame(oldItem: TopOffer, newItem: TopOffer): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: TopOffer, newItem: TopOffer): Boolean {
                return oldItem.title == newItem.title
            }
        }
    }

    class TopOfferViewHolder(private val binding: TopOfferRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(offerItem: TopOffer) = binding.apply {
            imgItem.load(offerItem.img)
            txtTitle.text = offerItem.title
            txtSubTitle.text = offerItem.subTitle
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopOfferViewHolder {
        return TopOfferViewHolder(
            TopOfferRowLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TopOfferViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
