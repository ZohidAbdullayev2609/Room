package com.example.room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.room.databinding.ActivityMain2Binding
import com.example.room.room.helper.RoomDbHelper
import com.example.room.room.model.User

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding
    private lateinit var roomDbHelper: RoomDbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        roomDbHelper = RoomDbHelper.DatabaseBuilder.getInstance(this)

        val userItem = intent.getSerializableExtra("user") as User
        binding.editName2.setText(userItem.name)
        binding.editEmail2.setText(userItem.email)

        binding.btnDelete.setOnClickListener {
            roomDbHelper.userDao().delete(userItem)

            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }

        binding.btnUpdate.setOnClickListener {
            val name = binding.editName2.text.toString()
            val email = binding.editEmail2.text.toString()

            if (name.isNotEmpty() && email.isNotEmpty()) {
                val user = User(userItem.id, name, email)
                roomDbHelper.userDao().update(user)
                startActivity(Intent(applicationContext, MainActivity::class.java))
                finish()
            }
        }
    }
}