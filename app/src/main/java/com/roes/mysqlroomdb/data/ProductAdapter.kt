package com.roes.mysqlroomdb.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.roes.mysqlroomdb.R

class ProductAdapter (private val products: List<Product>): RecyclerView.Adapter<ProductAdapter.myViewHolder>() {

    class myViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){
        val tvName: TextView = itemView.findViewById(R.id.tbName)
        val tvPrice: TextView = itemView.findViewById(R.id.tbPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.product_name, parent, false)

        return myViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val currentProduct = products[position]
        holder.tvName.text = currentProduct.name
        holder.tvPrice.text = currentProduct.price.toString()
    }

    override fun getItemCount(): Int {
        return products.size
    }

}