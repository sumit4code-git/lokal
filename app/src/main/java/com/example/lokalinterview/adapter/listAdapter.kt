package com.example.lokalinterview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lokalinterview.R
import com.example.lokalinterview.Response.article
import kotlinx.android.synthetic.main.card.view.*

class listAdapter: RecyclerView.Adapter<listAdapter.listViewHolder>(){
    inner class listViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private val differCallback=object : DiffUtil.ItemCallback<article>(){
        override fun areItemsTheSame(oldItem: article, newItem: article): Boolean {
            return oldItem.url==newItem.url
        }

        override fun areContentsTheSame(oldItem: article, newItem: article): Boolean {
            return oldItem==newItem
        }
    }
    val differ= AsyncListDiffer(this,differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): listViewHolder {
        return listViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.card,parent,false
            )
        )
    }
    override fun onBindViewHolder(holder: listViewHolder, position: Int) {
        val list=differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this.context).load("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg").into(imageView)
            Title.text=list.title
            setOnClickListener {
                onItemClickListener?.let{
                    it(list)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    private var onItemClickListener:((article)->Unit)?=null
    fun setOnItemClickListner(listner:(article)->Unit){
        onItemClickListener=listner
    }
}