package com.trendster.oyelabs.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.trendster.oyelabs.R
import com.trendster.oyelabs.databinding.GroceriesRowLayoutBinding
import com.trendster.oyelabs.ui.data.model.Item
import com.trendster.oyelabs.ui.homescreen.HomescreenFragmentDirections
import com.trendster.oyelabs.util.MyDiffUtil

class GroceriesAdapter : RecyclerView.Adapter<GroceriesAdapter.GroceriesViewHolder>() {

    private var groceriesList = emptyList<Item>()

    class GroceriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceriesViewHolder {
        val binding = parent.context.getSystemService(LayoutInflater::class.java)
            .inflate(R.layout.groceries_row_layout, parent, false)
        return GroceriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GroceriesViewHolder, position: Int) {
        val grocerieItem = groceriesList[position]
        GroceriesRowLayoutBinding.bind(holder.itemView).apply {
            imgItem.load(grocerieItem.img)
            txtTitle.text = grocerieItem.title
            imgBg.setColorFilter(ContextCompat.getColor(holder.itemView.context, (getColor(grocerieItem.title))))
            imgBg.setOnClickListener {
                val action = HomescreenFragmentDirections
                    .actionHomescreenFragmentToProductDetailFragment(grocerieItem)
                holder.itemView.findNavController().navigate(action)
            }
        }
    }

    private fun getColor(title: String): Int {
        return when (title) {
            "Pulses" -> R.color.pink
            "Rice" -> R.color.blue
            else -> R.color.pink
        }
    }

    override fun getItemCount(): Int {
        return groceriesList.size
    }

    fun setData(newList: List<Item>) {
        val myDiffUtil = MyDiffUtil(groceriesList, newList)
        val diffUtilResult = DiffUtil.calculateDiff(myDiffUtil)
        groceriesList = newList
        diffUtilResult.dispatchUpdatesTo(this)
    }
}
