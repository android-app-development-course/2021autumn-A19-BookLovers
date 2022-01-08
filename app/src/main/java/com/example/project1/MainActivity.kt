package com.example.project1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.activity_main2.homebtn
import kotlinx.android.synthetic.main.activity_main2.lv
import kotlinx.android.synthetic.main.activity_main2.matchbtn
import kotlinx.android.synthetic.main.activity_main2.messagebtn
import kotlinx.android.synthetic.main.activity_main2.postbtn
import kotlinx.android.synthetic.main.activity_main2.userbtn
import kotlinx.android.synthetic.main.activity_my_blog2.*
import kotlinx.android.synthetic.main.activity_register.*

class MainActivity : AppCompatActivity() {


    private val list1:ArrayList<String> = ArrayList()
    private val list2:ArrayList<String> =ArrayList()
    private val list3:ArrayList<Int> = ArrayList()
//    private val Username=intent.getStringExtra("username")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
         supportActionBar?.hide()
    val prefs = getSharedPreferences("user", Context.MODE_PRIVATE)
        val Username=prefs.getString("username","华师")

        val dbHelper=BlogDatabaseHelper(this,"blog_dh",1)
        val db = dbHelper.writableDatabase;
        val cursor = db.query("Blog", null, null, null, null, null, null)
        if (cursor.moveToFirst()) {
            do {
                // 遍历Cursor对象，取出数据并打印
                val writer = cursor.getString(cursor.getColumnIndex("writer"))
                list1.add(writer)
                val blogtitle = cursor.getString(cursor.getColumnIndex("blogtitle"))
                list2.add(blogtitle)
                val id=cursor.getInt(cursor.getColumnIndex("id"))
                list3.add(id)
            } while (cursor.moveToNext())
        }
        lv.adapter=MyAdapter(this,list1,list2)
        lv.setOnItemClickListener{ parent,view,position,id->
            //点击进入查看文章界面与评论界面
            val intent=Intent(this,CommentDetail::class.java)
            val p=list3[position]
            intent.putExtra("id",p)
            intent.putExtra("username",Username)
            startActivity(intent)
        }

        //主页
       homebtn.setOnClickListener {
           val intent=Intent(this,MainActivity::class.java)
           startActivity(intent)
       }
        //匹配
        matchbtn.setOnClickListener {
            val intent= Intent(this,Match::class.java)
            intent.putExtra("Username",Username)
            startActivity(intent)
        }
        //发布
        postbtn.setOnClickListener{
            val intent= Intent(this,Post::class.java)
            //传递个人信息，用户名
            intent.putExtra("username",Username)
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
            intent.putExtra("Username",Username)
            startActivity(intent)
        }

        db.close()
    }
}