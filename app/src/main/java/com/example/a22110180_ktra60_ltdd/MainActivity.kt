package com.example.a22110180_ktra60_ltdd

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a22110180_ktra60_ltdd.adapter.ProductAdapter
import com.example.a22110180_ktra60_ltdd.data.Product
import com.example.a22110180_ktra60_ltdd.repository.ProductRepository
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var productsRecyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private lateinit var productRepository: ProductRepository
    private var products: List<Product> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        productRepository = ProductRepository()
        setupRecyclerView()
        setupBottomNavigation()
        loadProducts()
    }

    private fun setupRecyclerView() {
        productsRecyclerView = findViewById(R.id.productsRecyclerView)
        productsRecyclerView.layoutManager = LinearLayoutManager(this)
        
        productAdapter = ProductAdapter(products) { product ->
            val intent = Intent(this, ProductDetailActivity::class.java)
            intent.putExtra("product", product)
            startActivity(intent)
        }
        
        productsRecyclerView.adapter = productAdapter
    }

    private fun setupBottomNavigation() {
        findViewById<LinearLayout>(R.id.navHome).setOnClickListener {
            // Already on home page
        }

        findViewById<LinearLayout>(R.id.navCart).setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }

        findViewById<LinearLayout>(R.id.navProfile).setOnClickListener {
            Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show()
        }

        findViewById<LinearLayout>(R.id.navSupport).setOnClickListener {
            Toast.makeText(this, "Support", Toast.LENGTH_SHORT).show()
        }

        findViewById<LinearLayout>(R.id.navSettings).setOnClickListener {
            Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadProducts() {
        lifecycleScope.launch {
            try {
                products = productRepository.getProducts()
                productAdapter = ProductAdapter(products) { product ->
                    val intent = Intent(this@MainActivity, ProductDetailActivity::class.java)
                    intent.putExtra("product", product)
                    startActivity(intent)
                }
                productsRecyclerView.adapter = productAdapter
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Error loading products: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}
