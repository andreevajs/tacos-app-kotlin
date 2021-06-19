package com.example.tacos.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tacos.R
import com.example.tacos.models.Product

class ProductAdapter() : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var _items = listOf<Product>()
    //lateinit var onBottomReachedListener: OnBottomReachedListener

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.setOnClickListener {
                //Navigation.findNavController(it).navigate(R.id.action_MainFragment_to_ListFragmentnt)
                //TODO navigate to product details fragment
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_product, parent, false)

        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = _items[position]
        holder.itemView.findViewById<TextView>(R.id.item_product_name).text = product.name
        holder.itemView.findViewById<TextView>(R.id.item_product_recipe).text = product.recipe

        //if (position == _items.size - 1)
        //    onBottomReachedListener?.onBottomReached(position)
    }

    override fun getItemCount(): Int {
        return _items.size
    }

    fun setItems(newItems : List<Product>) {
        _items = newItems
        notifyDataSetChanged()
    }

    fun addItems(newItems : List<Product>) {
        _items += newItems
        notifyDataSetChanged()
    }
}