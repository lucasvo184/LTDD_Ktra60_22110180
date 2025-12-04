package com.example.a22110180_ktra60_ltdd

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a22110180_ktra60_ltdd.adapter.CartAdapter
import com.example.a22110180_ktra60_ltdd.data.CartItem
import com.example.a22110180_ktra60_ltdd.repository.CartRepository
import com.example.a22110180_ktra60_ltdd.viewmodel.CartViewModel
import com.example.a22110180_ktra60_ltdd.viewmodel.CartViewModelFactory
import kotlinx.coroutines.launch

class CartActivity : AppCompatActivity() {
    private lateinit var cartRecyclerView: RecyclerView
    private lateinit var itemsCountTextView: TextView
    private lateinit var totalPriceTextView: TextView
    private lateinit var checkoutButton: Button
    private lateinit var cartRepository: CartRepository
    private lateinit var cartViewModel: CartViewModel
    private lateinit var cartAdapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        cartRepository = CartRepository(application)
        cartViewModel = ViewModelProvider(this, CartViewModelFactory(cartRepository))[CartViewModel::class.java]

        setupViews()
        setupRecyclerView()
        observeCartData()
        setupBottomNavigation()
    }

    private fun setupViews() {
        cartRecyclerView = findViewById(R.id.cartRecyclerView)
        itemsCountTextView = findViewById(R.id.itemsCountTextView)
        totalPriceTextView = findViewById(R.id.totalPriceTextView)
        checkoutButton = findViewById(R.id.checkoutButton)

        checkoutButton.setOnClickListener {
            Toast.makeText(this, "THANH TOÁN clicked!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupRecyclerView() {
        cartRecyclerView.layoutManager = LinearLayoutManager(this)
        
        cartAdapter = CartAdapter(
            cartItems = emptyList(),
            onQuantityChanged = { cartItem, newQuantity ->
                lifecycleScope.launch {
                    val updatedItem = cartItem.copy(quantity = newQuantity)
                    cartRepository.updateCartItem(updatedItem)
                }
            },
            onItemDeleted = { cartItem ->
                lifecycleScope.launch {
                    cartRepository.deleteCartItem(cartItem)
                }
            }
        )
        
        cartRecyclerView.adapter = cartAdapter
    }

    private fun observeCartData() {
        cartViewModel.getAllCartItems().observe(this) { cartItems ->
            cartAdapter = CartAdapter(
                cartItems = cartItems,
                onQuantityChanged = { cartItem, newQuantity ->
                    lifecycleScope.launch {
                        val updatedItem = cartItem.copy(quantity = newQuantity)
                        cartRepository.updateCartItem(updatedItem)
                    }
                },
                onItemDeleted = { cartItem ->
                    lifecycleScope.launch {
                        cartRepository.deleteCartItem(cartItem)
                    }
                }
            )
            cartRecyclerView.adapter = cartAdapter
        }

        cartViewModel.getCartItemCount().observe(this) { count ->
            itemsCountTextView.text = count.toString()
        }

        cartViewModel.getTotalPrice().observe(this) { total ->
            val totalPrice = total ?: 0.0
            totalPriceTextView.text = "$ ${String.format("%.2f", totalPrice)}"
        }
    }

    private fun setupBottomNavigation() {
        findViewById<LinearLayout>(R.id.navHome).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        findViewById<LinearLayout>(R.id.navCart).setOnClickListener {
            // Already on cart page
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

