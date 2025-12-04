package com.example.a22110180_ktra60_ltdd.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.a22110180_ktra60_ltdd.R
import com.example.a22110180_ktra60_ltdd.data.CartItem

class CartAdapter(
    private val cartItems: List<CartItem>,
    private val onQuantityChanged: (CartItem, Int) -> Unit,
    private val onItemDeleted: (CartItem) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cartItemImage: ImageView = itemView.findViewById(R.id.cartItemImage)
        val cartItemName: TextView = itemView.findViewById(R.id.cartItemName)
        val cartItemPrice: TextView = itemView.findViewById(R.id.cartItemPrice)
        val quantityTextView: TextView = itemView.findViewById(R.id.quantityTextView)
        val incrementButton: Button = itemView.findViewById(R.id.incrementButton)
        val decrementButton: Button = itemView.findViewById(R.id.decrementButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartItem = cartItems[position]
        holder.cartItemName.text = cartItem.productName
        holder.cartItemPrice.text = "$ ${String.format("%.2f", cartItem.productPrice)}"
        holder.quantityTextView.text = cartItem.quantity.toString()

        Glide.with(holder.itemView.context)
            .load(cartItem.productImage)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(holder.cartItemImage)

        holder.incrementButton.setOnClickListener {
            onQuantityChanged(cartItem, cartItem.quantity + 1)
        }

        holder.decrementButton.setOnClickListener {
            if (cartItem.quantity > 1) {
                onQuantityChanged(cartItem, cartItem.quantity - 1)
            } else {
                onItemDeleted(cartItem)
            }
        }
    }

    override fun getItemCount(): Int = cartItems.size
}

