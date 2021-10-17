package com.trendster.oyelabs.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.trendster.oyelabs.R
import com.trendster.oyelabs.databinding.GroceriesRowLayoutBinding
import com.trendster.oyelabs.ui.data.model.Item

class GroceriesAdapter(private val itemClick: (Item) -> Unit) :
    ListAdapter<Item, GroceriesAdapter.GroceriesViewHolder>(COMPARATOR) {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceriesViewHolder {
        return GroceriesViewHolder(
            GroceriesRowLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GroceriesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getColor(title: String): Int {
        return when (title) {
            "Pulses" -> R.color.pink
            "Rice" -> R.color.blue
            else -> R.color.pink
        }
    }

    inner class GroceriesViewHolder(private val binding: GroceriesRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(grocerieItem: Item) = binding.apply {
            val context = root.context
            imgItem.load(grocerieItem.img)
            txtTitle.text = grocerieItem.title
            imgBg.setColorFilter(ContextCompat.getColor(context, (getColor(grocerieItem.title))))
            imgBg.setOnClickListener {
                itemClick.invoke(grocerieItem)
            }
        }
    }
}
