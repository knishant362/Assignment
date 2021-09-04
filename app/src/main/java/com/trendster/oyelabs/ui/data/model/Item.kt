package com.trendster.oyelabs.ui.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(
    val img: Int = 0,
    val title: String = "",
    val description: String = "",
    val price: Double = 1.0
) : Parcelable
