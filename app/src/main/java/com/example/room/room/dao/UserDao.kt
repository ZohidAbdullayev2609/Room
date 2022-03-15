package com.example.room.room.dao

import androidx.room.*
import com.example.room.room.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(user: User):Long

    @Query("select * from users order by users.id")
    fun getAllUser(): List<User>

    @Delete
    fun delete(user: User)

    @Update
    fun update(user: User)

    @Query("select * from users order by users.name")
    fun sortedListAsc(): List<User>

    @Query("select * from users order by users.name desc")
    fun sortedListDesc(): List<User>
}