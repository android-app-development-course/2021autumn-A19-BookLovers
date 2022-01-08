package com.example.project1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_match.*

class Match : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match)
        supportActionBar?.hide()
        val Username=intent.getStringExtra("Username")
        matchbtn.setOnClickListener {
            val School = et1.text.toString()
            val Sex=et2.text.toString()
            val Age=et3.text.toString()
            val intent= Intent(this,Match_Result::class.java)
            intent.putExtra("School",School)
            intent.putExtra("Sex",Sex)
            intent.putExtra("Age",Age)
            intent.putExtra("Username",Username)
            startActivity(intent)
        }
        cancel_button.setOnClickListener {
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}