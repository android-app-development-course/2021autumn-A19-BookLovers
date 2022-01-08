package com.example.project1

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_user_msg.*

class user_msg : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_msg)
        supportActionBar?.hide()
        val prefs = getSharedPreferences("user", Context.MODE_PRIVATE)
        username.text = prefs.getString("username", "华师")
        sex.text = prefs.getString("sex", "人妖")
        age.text = prefs.getInt("age", 0).toString()
        school_name.text = prefs.getString("school_name", "未填")
    }
}