package com.example.a22110180_ktra60_ltdd.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class CartItem(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val productId: Int,
    val productName: String,
    val productPrice: Double,
    val productImage: String,
    val quantity: Int
) {
    fun getTotalPrice(): Double {
        return productPrice * quantity
    }
}

