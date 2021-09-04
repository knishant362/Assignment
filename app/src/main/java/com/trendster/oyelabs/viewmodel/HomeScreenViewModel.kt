package com.trendster.oyelabs.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trendster.oyelabs.R
import com.trendster.oyelabs.ui.data.model.Item
import com.trendster.oyelabs.ui.data.model.TopOffer

class HomeScreenViewModel : ViewModel() {

    private val _topOfferResponse = MutableLiveData<List<TopOffer>>()
    val topOfferResponse: LiveData<List<TopOffer>> get() = _topOfferResponse

    private val _exclusiveOfferResponse = MutableLiveData<List<Item>>()
    val exclusiveOfferResponse: LiveData<List<Item>> get() = _exclusiveOfferResponse

    private val _groceriesResponse = MutableLiveData<List<Item>>()
    val groceriesResponse: LiveData<List<Item>> get() = _groceriesResponse

    private val _bestSellingResponse = MutableLiveData<List<Item>>()
    val bestSellingResponse: LiveData<List<Item>> get() = _bestSellingResponse

    fun fetchTopOffers() {
        _topOfferResponse.postValue(demoTopOfferData())
    }

    fun fetchExclusiveOffers() {
        _exclusiveOfferResponse.postValue(demoExclusiveOfferData())
    }

    fun fetchBestSelling() {
        _bestSellingResponse.postValue(demoBestSellingData())
    }

    fun fetchGroceries() {
        _groceriesResponse.postValue(demoGroceriesData())
    }

    private fun demoGroceriesData(): List<Item> {
        val list = mutableListOf<Item>()
        list.add(Item(R.drawable.ic_pulses, "Pulses", "7pcs / Price", 4.99))
        list.add(Item(R.drawable.ic_rice, "Rice", "1Kg / Price", 4.99))
        list.add(Item(R.drawable.ic_pulses, "Pulses", "7pcs / Price", 5.44))
        list.add(Item(R.drawable.ic_rice, "Rice", "1Kg / Price", 2.00))
        return list
    }

    private fun demoBestSellingData(): List<Item> {
        val list = mutableListOf<Item>()
        list.add(Item(R.drawable.ic_pepper, "Bell Pepper Red", "7pcs / Price", 4.99))
        list.add(Item(R.drawable.ic_ginger, "Ginger", "1Kg / Price", 4.99))
        list.add(Item(R.drawable.ic_pepper, "Bell Pepper Red", "7pcs / Price", 3.99))
        list.add(Item(R.drawable.ic_ginger, "Ginger", "1Kg / Price", 7.29))
        return list
    }

    private fun demoTopOfferData(): MutableList<TopOffer> {
        val list = mutableListOf<TopOffer>()
        list.add(TopOffer(R.drawable.ic_vegetable, "Fresh Vegetables", "Get Up To 40% OFF"))
        list.add(TopOffer(R.drawable.ic_vegetable, "Fresh Vegetables", "Get Up To 40% OFF"))
        list.add(TopOffer(R.drawable.ic_vegetable, "Fresh Vegetables", "Get Up To 40% OFF"))
        return list
    }

    private fun demoExclusiveOfferData(): MutableList<Item> {
        val list = mutableListOf<Item>()
        list.add(Item(R.drawable.ic_banana, "Organic Bananas", "7pcs / Price", 4.99))
        list.add(Item(R.drawable.ic_apple, "Red Apple", "1Kg / Price", 2.99))
        list.add(Item(R.drawable.ic_banana, "Organic Bananas", "7pcs / Price", 4.99))
        list.add(Item(R.drawable.ic_apple, "Red Apple", "1Kg / Price", 2.99))
        return list
    }
}
