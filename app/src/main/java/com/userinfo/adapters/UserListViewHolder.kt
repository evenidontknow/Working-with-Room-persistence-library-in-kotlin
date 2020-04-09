package com.userinfo.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.userinfo.R

class UserListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var userName: TextView = itemView.findViewById(R.id.txtName)
    var userEmail: TextView = itemView.findViewById(R.id.txtEmail)


}