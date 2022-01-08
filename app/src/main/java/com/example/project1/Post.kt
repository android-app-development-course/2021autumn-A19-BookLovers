package com.example.project1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_post.*


class Post : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        supportActionBar?.hide()
        val prefs = getSharedPreferences("user", Context.MODE_PRIVATE)
        val Writer=prefs.getString("username","华师")
        postbtn.setOnClickListener {

            val title=et1.text.toString()
            val content=editTextTextMultiLine.text.toString()

            val dbHelper=BlogDatabaseHelper(this,"blog_dh",1)
            val db = dbHelper.writableDatabase

            db.execSQL("insert into Blog (blogtitle,blogcontent,writer) values(?, ?, ?)", arrayOf(title, content, Writer))
            Toast.makeText(this, "发布成功", Toast.LENGTH_SHORT).show()
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)

        }
        cancel_button.setOnClickListener {
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        }




    }
}