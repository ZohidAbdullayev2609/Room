package com.example.room.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.room.R
import com.example.room.room.model.User

class MyRecycleAdapter(var list: List<User>, var onClick: (user: User) -> Unit) :
    RecyclerView.Adapter<MyRecycleAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(user: User) {
            val name = itemView.findViewById<AppCompatTextView>(R.id.tv_name)
            val email = itemView.findViewById<AppCompatTextView>(R.id.tv_email)

            itemView.apply {
                name.text = user.name
                email.text = user.email
            }

            itemView.setOnClickListener {
                onClick(user)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size
}