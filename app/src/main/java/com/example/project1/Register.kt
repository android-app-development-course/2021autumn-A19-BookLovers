package com.example.project1

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity.apply
import android.widget.Toast
import androidx.core.view.GravityCompat.apply
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.hide()
        btn_register.setOnClickListener {
            if (passwd1.text.toString() != passwd2.text.toString()){
                Toast.makeText(this, "密码不一致，请重新输入", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val userName = username.text.toString()
            if (userName == ""){
                Toast.makeText(this, "用户名为空", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val schoolName = school.text.toString()
            val sx = sex.text.toString()
            val age = age.text.toString().toInt()
            val password = passwd1.text.toString()
            val userHelper = UserDatabaseHelper(this, "user_db", 1)
            val db = userHelper.writableDatabase
            val cur = db.query("user", arrayOf("username"), "username=?", arrayOf(userName), null, null, null, null)
            if(cur.moveToNext()){
                Toast.makeText(this, "用户名已存在", Toast.LENGTH_SHORT).show()
                db.close()
                return@setOnClickListener
            }
            val values = ContentValues().apply {
                put("username", userName)
                put("password", password)
                put("school_name", schoolName)
                put("sex", sx)
                put("age", age)
            }
            db.insert("user", null, values)
            db.close()
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }
}