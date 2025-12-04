package com.example.a22110180_ktra60_ltdd.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.a22110180_ktra60_ltdd.data.AppDatabase
import com.example.a22110180_ktra60_ltdd.data.CartItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CartRepository(application: Application) {
    private val cartItemDao = AppDatabase.getDatabase(application).cartItemDao()

    fun getAllCartItems(): LiveData<List<CartItem>> {
        return cartItemDao.getAllCartItems()
    }

    suspend fun addToCart(productId: Int, productName: String, productPrice: Double, productImage: String, quantity: Int) {
        withContext(Dispatchers.IO) {
            val existingItem = cartItemDao.getCartItemByProductId(productId)
            if (existingItem != null) {
                val updatedItem = existingItem.copy(quantity = existingItem.quantity + quantity)
                cartItemDao.updateCartItem(updatedItem)
            } else {
                val newItem = CartItem(
                    productId = productId,
                    productName = productName,
                    productPrice = productPrice,
                    productImage = productImage,
                    quantity = quantity
                )
                cartItemDao.insertCartItem(newItem)
            }
        }
    }

    suspend fun updateCartItem(cartItem: CartItem) {
        withContext(Dispatchers.IO) {
            cartItemDao.updateCartItem(cartItem)
        }
    }

    suspend fun deleteCartItem(cartItem: CartItem) {
        withContext(Dispatchers.IO) {
            cartItemDao.deleteCartItem(cartItem)
        }
    }

    suspend fun deleteCartItemById(id: Long) {
        withContext(Dispatchers.IO) {
            cartItemDao.deleteCartItemById(id)
        }
    }

    fun getCartItemCount(): LiveData<Int> {
        return cartItemDao.getCartItemCount()
    }

    fun getTotalPrice(): LiveData<Double?> {
        return cartItemDao.getTotalPrice()
    }
}

