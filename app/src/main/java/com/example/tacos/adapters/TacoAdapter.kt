package com.example.tacos.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tacos.R
import com.example.tacos.models.Taco

class TacoAdapter(
    private val onItemClick: (Taco) -> Unit,
    private val onItemDeleteClick: (Taco) -> Unit
) : RecyclerView.Adapter<TacoAdapter.TacoViewHolder>() {

    private var _items = listOf<Taco>()

    class TacoViewHolder(view: View,
                         private val onClick: (Taco) -> Unit,
                         private val onDeleteClick: (Taco) -> Unit)
        : RecyclerView.ViewHolder(view) {
        private var _item : Taco? = null

        init {
            view.setOnClickListener { _item?.let(onClick) }
            val deleteButton = view.findViewById<Button>(R.id.btn_delete_taco)
            deleteButton.setOnClickListener{ _item?.let(onDeleteClick) }
        }

        fun bind(item: Taco) {
            _item = item

            itemView.findViewById<TextView>(R.id.tacos_base_layer).text = item.baseLayer.name
            itemView.findViewById<TextView>(R.id.tacos_mixin).text = item.mixin.name
            itemView.findViewById<TextView>(R.id.tacos_condiment).text = item.condiment.name
            itemView.findViewById<TextView>(R.id.tacos_seasoning).text = item.seasoning.name
            itemView.findViewById<TextView>(R.id.tacos_shell).text = item.shell.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TacoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_taco, parent, false)

        return TacoViewHolder(view, onItemClick, onItemDeleteClick)
    }

    override fun onBindViewHolder(holder: TacoViewHolder, position: Int) {
        val taco = _items[position]
        holder.bind(taco)
    }

    override fun getItemCount(): Int {
        return _items.size
    }

    fun setItems(newItems : List<Taco>) {
        _items = newItems
        notifyDataSetChanged()
    }
}