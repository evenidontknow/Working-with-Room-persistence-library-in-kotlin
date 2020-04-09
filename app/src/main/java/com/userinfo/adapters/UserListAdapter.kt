package com.userinfo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView
import com.userinfo.R
import com.userinfo.database.User


class UserListAdapter(private var userList: List<User>) :

    RecyclerView.Adapter<UserListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {

        val layoutView =
            LayoutInflater.from(parent.context).inflate(R.layout.user_list_item, parent, false)
        return UserListViewHolder(layoutView)
    }

    override fun getItemCount(): Int {

        return userList.size
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {

        if (position < userList.size) {
            val user = userList[position]
            holder.userName.text = user.userName
            holder.userEmail.text = user.userEmail
        }
    }
}