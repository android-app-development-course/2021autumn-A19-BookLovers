package com.example.project1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.btn_register
import kotlinx.android.synthetic.main.activity_register.*

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        btn_register.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
        btn_login.setOnClickListener {
            val userName = et1.text.toString()
            val password = et2.text.toString()
            val userHelper = UserDatabaseHelper(this, "user_db", 1)
            val db = userHelper.writableDatabase
            val cur = db.query("user", arrayOf("password", "school_name", "sex", "age"),
                "username=?", arrayOf(userName), null, null, null, null)
            if(cur.moveToNext()){
                if (password == cur.getString(0)){
                    val editor = getSharedPreferences("user", Context.MODE_PRIVATE).edit()
                    editor.putString("username", userName)
                    editor.putString("school_name", cur.getString(1).toString())
                    editor.putString("sex", cur.getString(2).toString())
                    editor.putInt("age", cur.getInt(3))
                    editor.apply()
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("username",userName)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(this, "密码错误", Toast.LENGTH_SHORT).show()
                }
                db.close()
                return@setOnClickListener
            }
            db.close()
            Toast.makeText(this, "用户名不存在", Toast.LENGTH_SHORT).show()
        }
    }
}