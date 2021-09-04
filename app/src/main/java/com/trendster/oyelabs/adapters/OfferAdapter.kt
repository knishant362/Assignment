package com.trendster.oyelabs.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.trendster.oyelabs.R
import com.trendster.oyelabs.databinding.OfferRowLayoutBinding
import com.trendster.oyelabs.ui.data.model.Item
import com.trendster.oyelabs.util.MyDiffUtil

class OfferAdapter : RecyclerView.Adapter<OfferAdapter.OfferViewHolder>() {

    private var offerItemList = emptyList<Item>()

    class OfferViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        val binding = parent.context.getSystemService(LayoutInflater::class.java)
            .inflate(R.layout.offer_row_layout, parent, false)
        return OfferViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        val offerItem = offerItemList[position]
        OfferRowLayoutBinding.bind(holder.itemView).apply {
            imgItem.load(offerItem.img)
            txtItemName.text = offerItem.title
            txtItemDesc.text = offerItem.description
            txtPrice.text = offerItem.price.toString() + " $"
        }
    }

    override fun getItemCount(): Int {
        return offerItemList.size
    }

    fun setData(newList: List<Item>) {
        val myDiffUtil = MyDiffUtil(offerItemList, newList)
        val diffUtilResult = DiffUtil.calculateDiff(myDiffUtil)
        offerItemList = newList
        diffUtilResult.dispatchUpdatesTo(this)
    }
}
