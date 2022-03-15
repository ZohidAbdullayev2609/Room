package com.example.room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.room.adapter.MyRecycleAdapter
import com.example.room.databinding.ActivityMainBinding
import com.example.room.room.helper.RoomDbHelper
import com.example.room.room.model.User

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MyRecycleAdapter
    private lateinit var roomDbHelper: RoomDbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        roomDbHelper = RoomDbHelper.DatabaseBuilder.getInstance(this)

        setAdapter(roomDbHelper.userDao().getAllUser())

        binding.btnAdd.setOnClickListener {
            val name = binding.editName.text.toString()
            val email = binding.editEmail.text.toString()

            if (name.isNotEmpty() && email.isNotEmpty()) {
                val user = User(null, name, email)
                roomDbHelper.userDao().insertUsers(user)
                Toast.makeText(this, "Add user", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnRead.setOnClickListener {
            setAdapter(roomDbHelper.userDao().getAllUser())
        }
    }

    private fun setAdapter(list: List<User>) {
        adapter = MyRecycleAdapter(list) {
            val intent = Intent(applicationContext, MainActivity2::class.java)
            intent.putExtra("user", it)
            startActivity(intent)
            finish()
        }
        binding.rvUsers.adapter = adapter
    }
}