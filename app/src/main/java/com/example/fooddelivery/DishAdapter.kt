package com.example.fooddelivery

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class DishAdapter(var dishArrayList: ArrayList<dish>, context: Activity) : RecyclerView.Adapter<DishAdapter.ViewHolder>() {
    private lateinit var myListener: onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(listener: onItemClickListener){
        myListener = listener
    }
    class ViewHolder(itemView:View,listener: onItemClickListener):RecyclerView.ViewHolder(itemView) {
        val ivDishImage =  itemView.findViewById<ShapeableImageView>(R.id.ivDish)
        val tvdish = itemView.findViewById<TextView>(R.id.tvdishName)
        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.dishitem,parent,false)
        return ViewHolder(itemView,myListener)
    }

    override fun onBindViewHolder(holder: DishAdapter.ViewHolder, position: Int) {
        var currentItem = dishArrayList[position]
        holder.ivDishImage.setImageResource(currentItem.dishImg)
        holder.tvdish.text = currentItem.dishName
    }

    override fun getItemCount(): Int {
        return dishArrayList.size
    }

}
