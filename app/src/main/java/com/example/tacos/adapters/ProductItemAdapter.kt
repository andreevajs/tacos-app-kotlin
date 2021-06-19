package com.example.tacos.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tacos.R
import com.example.tacos.models.Product
import com.example.tacos.models.ProductItem

class ProductItemAdapter(
    private val onItemClick: (ProductItem) -> Unit
) : RecyclerView.Adapter<ProductItemAdapter.ProductViewHolder>() {

    private var _items = listOf<ProductItem>()

    class ProductViewHolder(view: View, val onClick: (ProductItem) -> Unit)
        : RecyclerView.ViewHolder(view) {
        private var _productItem : ProductItem? = null

        init {
            view.setOnClickListener {
                _productItem?.let(onClick)
            }
        }

        fun bind(productItem: ProductItem) {
            _productItem = productItem

            itemView.findViewById<TextView>(R.id.item_product_name).text = productItem.product.name
            itemView.findViewById<TextView>(R.id.item_product_tags).text = productItem.tags
            itemView.findViewById<TextView>(R.id.item_product_recipe).text = productItem.ingredients.joinToString("\n")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_product, parent, false)

        return ProductViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = _items[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return _items.size
    }

    fun setItems(newItems : List<ProductItem>) {
        _items = newItems
        notifyDataSetChanged()
    }
}