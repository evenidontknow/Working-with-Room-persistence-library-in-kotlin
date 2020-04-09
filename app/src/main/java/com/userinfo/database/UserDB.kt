package com.userinfo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class UserDB: RoomDatabase()
{
    abstract fun getDAO(): UserDAO

    companion object
    {
        @Volatile private var instance: UserDB? = null

        operator fun invoke(context: Context) = instance?: synchronized(Any())
        {
            instance?: createDB(context).also {
                it
            }
        }

        private fun createDB(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            UserDB::class.java,
            "userdb"
        ).build()
    }
}