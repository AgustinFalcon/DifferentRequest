package com.example.jugandopostrequest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jugandopostrequest.databinding.ItemRecyclerviewBinding
import com.example.jugandopostrequest.model.Post


class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    private var myList = emptyList<Post>()

    inner class MyViewHolder(val binding: ItemRecyclerviewBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.tvUserId.text = myList[position].userId.toString()
        holder.binding.tvId.text = myList[position].id.toString()
        holder.binding.tvTitle.text = myList[position].title
        holder.binding.tvBody.text = myList[position].body
    }

    fun setData(newList: List<Post>){
        myList = newList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = myList.size
}