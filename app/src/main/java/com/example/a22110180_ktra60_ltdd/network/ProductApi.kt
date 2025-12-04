package com.example.a22110180_ktra60_ltdd.network

import com.example.a22110180_ktra60_ltdd.data.Product
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {
    @GET("products")
    suspend fun getProducts(): List<Product>

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): Product
}

