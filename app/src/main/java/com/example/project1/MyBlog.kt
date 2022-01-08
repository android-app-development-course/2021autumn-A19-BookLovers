package com.example.project1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.activity_main2.homebtn
import kotlinx.android.synthetic.main.activity_main2.matchbtn
import kotlinx.android.synthetic.main.activity_main2.messagebtn
import kotlinx.android.synthetic.main.activity_main2.postbtn
import kotlinx.android.synthetic.main.activity_main2.userbtn
import kotlinx.android.synthetic.main.activity_message1.*
import kotlinx.android.synthetic.main.activity_my.*

class MyBlog : AppCompatActivity() {
    private val list1:ArrayList<String> = ArrayList()
    private val list2:ArrayList<String> =ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_blog2)
        supportActionBar?.hide()
        val prefs = getSharedPreferences("user", Context.MODE_PRIVATE)
        val username= prefs.getString("username","华师")

        val dbHelper=BlogDatabaseHelper(this,"blog_dh",1)
        val db = dbHelper.writableDatabase;
        val cursor = db.query("Blog", null, null, null, null, null, null)
        if (cursor.moveToFirst()) {
            do {
                // 遍历Cursor对象，取出数据并打印
                val writer = cursor.getString(cursor.getColumnIndex("writer"))
                if(writer==username) {
                    list1.add(0, writer)
                    val blogtitle = cursor.getString(cursor.getColumnIndex("blogtitle"))
                    list2.add(0, blogtitle)
                }
            } while (cursor.moveToNext())
        }
        lv.adapter=MyAdapter(this,list1,list2)
        homebtn.setOnClickListener {
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        //匹配
        matchbtn.setOnClickListener {
            val intent= Intent(this,Match::class.java)

            startActivity(intent)
        }
        //发布
        postbtn.setOnClickListener{
            val intent= Intent(this,Post::class.java)
            //传递个人信息，用户名

            startActivity(intent)
        }

        //消息
        messagebtn.setOnClickListener {
            val intent= Intent(this,Message1::class.java)
            startActivity(intent)
        }
        //我的
        userbtn.setOnClickListener {
            val intent= Intent(this,My::class.java)

            startActivity(intent)
        }

    }
}