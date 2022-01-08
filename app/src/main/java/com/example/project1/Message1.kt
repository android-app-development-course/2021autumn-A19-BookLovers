package com.example.project1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.activity_message1.*
import kotlinx.android.synthetic.main.activity_message1.homebtn
import kotlinx.android.synthetic.main.activity_message1.matchbtn
import kotlinx.android.synthetic.main.activity_message1.messagebtn
import kotlinx.android.synthetic.main.activity_message1.postbtn
import kotlinx.android.synthetic.main.activity_message1.userbtn


class Message1 : AppCompatActivity() {
    private val list1:ArrayList<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message1)
        supportActionBar?.hide()
        val prefs = getSharedPreferences("user", Context.MODE_PRIVATE)
        val username = prefs.getString("username","华师")
        val idlist:ArrayList<Int> = ArrayList()
        val dbHelper=BlogDatabaseHelper(this,"blog_dh",1)
        val db = dbHelper.writableDatabase;

        val cursor = db.query("Blog", null, null, null, null, null, null)

        if (cursor.moveToFirst()) {
            do {
                // 遍历Cursor对象，取出数据并打印

                val writer=cursor.getString(cursor.getColumnIndex("writer"))

                if(writer==username)
                {
                    val id=cursor.getInt(cursor.getColumnIndex("id"))
                    idlist.add(id)
                }

            } while (cursor.moveToNext())
        }

        val dBhelper1=comDBhelper(this,"comment2",1)
        val db1=dBhelper1.writableDatabase

        val cursor1=db1.query("comment2",null,null,null,null,null,null,null)
        if (cursor1.moveToFirst()) {
            do {
                // 遍历Cursor1对象，取出数据并打印

                val id=cursor1.getInt(cursor1.getColumnIndex("id"))

                if(idlist.contains(id))
                {
                    val commenter=cursor1.getString(cursor1.getColumnIndex("username"))
                    list1.add(commenter)
                }

            } while (cursor1.moveToNext())
        }
        messagelv.adapter=MessageAdapter(this,list1)

        //主页
        homebtn.setOnClickListener {
            val intent=Intent(this,MainActivity::class.java)
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
            val intent=Intent(this,Message1::class.java)
            startActivity(intent)
        }
        //我的
        userbtn.setOnClickListener {
            val intent=Intent(this,My::class.java)

            startActivity(intent)
        }

    }



}