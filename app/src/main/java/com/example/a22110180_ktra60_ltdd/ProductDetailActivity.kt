package com.example.a22110180_ktra60_ltdd

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.a22110180_ktra60_ltdd.data.Product
import com.example.a22110180_ktra60_ltdd.repository.CartRepository
import kotlinx.coroutines.launch

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var product: Product
    private var quantity: Int = 1
    private lateinit var cartRepository: CartRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        product = intent.getParcelableExtra("product") ?: return
        cartRepository = CartRepository(application)

        setupViews()
        setupBottomNavigation()
    }

    private fun setupViews() {
        findViewById<TextView>(R.id.productNameTextView).text = product.name
        findViewById<TextView>(R.id.productPriceTextView).text = "$ ${String.format("%.2f", product.price)}"
        findViewById<TextView>(R.id.productDescriptionTextView).text = product.description

        Glide.with(this)
            .load(product.image)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(findViewById(R.id.productImageView))

        val quantityTextView = findViewById<TextView>(R.id.quantityTextView)
        quantityTextView.text = quantity.toString()

        findViewById<Button>(R.id.incrementButton).setOnClickListener {
            quantity++
            quantityTextView.text = quantity.toString()
        }

        findViewById<Button>(R.id.decrementButton).setOnClickListener {
            if (quantity > 1) {
                quantity--
                quantityTextView.text = quantity.toString()
            }
        }

        findViewById<Button>(R.id.addToCartButton).setOnClickListener {
            addToCart()
        }
    }

    private fun addToCart() {
        lifecycleScope.launch {
            cartRepository.addToCart(
                productId = product.id,
                productName = product.name,
                productPrice = product.price,
                productImage = product.image,
                quantity = quantity
            )
            Toast.makeText(this@ProductDetailActivity, "Added to cart!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupBottomNavigation() {
        findViewById<LinearLayout>(R.id.navHome).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
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
}

