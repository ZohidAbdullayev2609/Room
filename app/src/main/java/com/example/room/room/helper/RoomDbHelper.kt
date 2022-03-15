package com.example.room.room.helper

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.room.room.dao.UserDao
import com.example.room.room.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class RoomDbHelper : RoomDatabase() {

    abstract fun userDao(): UserDao

    object DatabaseBuilder {
        private var instance: RoomDbHelper? = null

        fun getInstance(context: Context): RoomDbHelper {

            if (instance == null) {
                synchronized(this) {
                    instance = buildRoomDb(context)
                }
            }
            return instance!!
        }

        private fun buildRoomDb(context: Context): RoomDbHelper =
            Room.databaseBuilder(
                context.applicationContext,
                RoomDbHelper::class.java,
                "room_users_db"
            )
                .allowMainThreadQueries()
                .build()
    }
}