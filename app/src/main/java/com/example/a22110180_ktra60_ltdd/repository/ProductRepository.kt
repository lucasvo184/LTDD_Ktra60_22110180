package com.example.a22110180_ktra60_ltdd.repository

import com.example.a22110180_ktra60_ltdd.data.Product
import com.example.a22110180_ktra60_ltdd.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductRepository {
    suspend fun getProducts(): List<Product> = withContext(Dispatchers.IO) {
        try {
            RetrofitClient.productApi.getProducts()
        } catch (e: Exception) {
            // Fallback to mock data if API fails
            RetrofitClient.getMockProducts()
        }
    }

    suspend fun getProductById(id: Int): Product? = withContext(Dispatchers.IO) {
        try {
            RetrofitClient.productApi.getProductById(id)
        } catch (e: Exception) {
            // Fallback to mock data
            RetrofitClient.getMockProducts().find { it.id == id }
        }
    }
}

