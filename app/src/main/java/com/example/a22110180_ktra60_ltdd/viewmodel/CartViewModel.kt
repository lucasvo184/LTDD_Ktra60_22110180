package com.example.a22110180_ktra60_ltdd.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.a22110180_ktra60_ltdd.data.CartItem
import com.example.a22110180_ktra60_ltdd.repository.CartRepository

class CartViewModel(private val cartRepository: CartRepository) : ViewModel() {
    fun getAllCartItems(): LiveData<List<CartItem>> {
        return cartRepository.getAllCartItems()
    }

    fun getCartItemCount(): LiveData<Int> {
        return cartRepository.getCartItemCount()
    }

    fun getTotalPrice(): LiveData<Double?> {
        return cartRepository.getTotalPrice()
    }
}

