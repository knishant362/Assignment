package com.trendster.oyelabs.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.trendster.oyelabs.R
import com.trendster.oyelabs.databinding.TopOfferRowLayoutBinding
import com.trendster.oyelabs.ui.data.model.TopOffer
import com.trendster.oyelabs.util.MyDiffUtil

class TopOfferAdapter : RecyclerView.Adapter<TopOfferAdapter.TopOfferViewHolder>() {

    private var offerItemList = emptyList<TopOffer>()

    class TopOfferViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopOfferViewHolder {
        val binding = parent.context.getSystemService(LayoutInflater::class.java)
            .inflate(R.layout.top_offer_row_layout, parent, false)
        return TopOfferViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopOfferViewHolder, position: Int) {
        val offerItem = offerItemList[position]
        TopOfferRowLayoutBinding.bind(holder.itemView).apply {
            imgItem.load(offerItem.img)
            txtTitle.text = offerItem.title
            txtSubTitle.text = offerItem.subTitle
        }
    }

    override fun getItemCount(): Int {
        return offerItemList.size
    }

    fun setData(newList: List<TopOffer>) {
        val myDiffUtil = MyDiffUtil(offerItemList, newList)
        val diffUtilResult = DiffUtil.calculateDiff(myDiffUtil)
        offerItemList = newList
        diffUtilResult.dispatchUpdatesTo(this)
    }
}
