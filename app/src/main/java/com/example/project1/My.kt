package com.example.project1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_my.*

class My : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my)
        supportActionBar?.hide()
        val prefs = getSharedPreferences("user", Context.MODE_PRIVATE)
        my_name.text = prefs.getString("username","华师")
        col.setOnClickListener {
            val intent = Intent(this, user_msg::class.java)
            startActivity(intent)
        }
        my_pub.setOnClickListener {
            val intent=Intent(this,MyBlog::class.java)
            startActivity(intent)
        }
    }
}