package com.userinfo.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class User(val userName: String, val userEmail: String): Serializable
{
    @PrimaryKey(autoGenerate = true)
    var id: Int=0
}