package com.example.rxmvvm3app.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.rxmvvm3app.R
import com.example.rxmvvm3app.data.network.model.User
import com.example.rxmvvm3app.databinding.ActivityMainBinding
import com.example.rxmvvm3app.databinding.RvItemCardBinding

class MainAdapter : RecyclerView.Adapter<MainViewHolder>() {

    var usersList = mutableListOf<User>()
    fun setData(data: List<User>) {
        this.usersList = data.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RvItemCardBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val user = usersList[position]
        holder.binding.textUserName.text = user.firstName +" "+user.lastName
        holder.binding.textUserEmail.text = user.email
        Glide.with(holder.itemView.context)
            .load(user.avatar)
            .placeholder(R.drawable.placeholder)
            .into(holder.binding.imageViewPoster)
    }
}

class MainViewHolder(val binding: RvItemCardBinding): ViewHolder(binding.root) {

}
