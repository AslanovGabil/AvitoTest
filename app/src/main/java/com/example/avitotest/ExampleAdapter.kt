package com.example.avitotest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.exemple_item.view.*

class ExampleAdapter(
    private val exampleList: ArrayList<ExampleItem>,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.exemple_item,
            parent, false
        )

        return ExampleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = exampleList[position]
        holder.imageView.setImageResource(currentItem.ImageResource)
        holder.textView1.text = currentItem.Number.toString()
        holder.textView2.text = currentItem.Text
    }



    override fun getItemCount() = exampleList.size
    inner class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val imageView: ImageView = itemView.image_view
        val textView1: TextView = itemView.text_view_1
        val textView2: TextView = itemView.text_view_2
        val imageDeleteView: ImageView = itemView.image_delete_view

        init {
            imageDeleteView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onRemoveClick(position)
            }
        }
    }

    interface OnItemClickListener {
        //fun onItemClick(position: Int)
        fun onRemoveClick(position: Int)
    }
}


