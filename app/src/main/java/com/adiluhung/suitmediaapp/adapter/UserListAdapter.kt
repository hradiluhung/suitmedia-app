package com.adiluhung.suitmediaapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.adiluhung.suitmediaapp.data.User
import com.adiluhung.suitmediaapp.databinding.ItemUserBinding
import com.bumptech.glide.Glide

class UserListAdapter(val onClick: (User) -> Unit) :
    PagingDataAdapter<User, UserListAdapter.MyViewHolder>(DIFF_CALLBACK) {

    class MyViewHolder(val onClick: (User) -> Unit, private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: User) {
            binding.tvFirstname.text = data.firstName
            binding.tvLastname.text = data.lastName
            binding.tvEmail.text = data.email
            Glide.with(binding.root.context)
                .load(data.avatar)
                .into(binding.ivImage)

            binding.root.setOnClickListener {
                onClick(data)
            }
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(onClick, binding)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}