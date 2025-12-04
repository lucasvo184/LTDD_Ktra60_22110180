package com.example.a22110180_ktra60_ltdd.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val image: String,
    val description: String
) : Parcelable

