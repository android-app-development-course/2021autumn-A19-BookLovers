package com.example.project1

import android.annotation.SuppressLint
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_input_comment.*
import kotlin.math.log

class input_comment : AppCompatActivity() {
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_comment)
        if (supportActionBar != null){
            supportActionBar?.hide();
        }
        //获取id
        val id=intent.getIntExtra("id",0)
        val username=intent.getStringExtra("username")
        comment_bt.setOnClickListener{
            val content = comment_et.text.toString()

            //写取数据库评论
            val dbhelper=comDBhelper(this,"comment2",1)
            val db = dbhelper.writableDatabase
            val values = ContentValues().apply {
                put("id", id)
                put("username", username)
                put("content", content)
            }
            db.insert("comment2", null, values)
            finish()
//            Log.d("inputcomment","文本框内容是$content id是$id 名字是$username")
        }
        btn_return.setOnClickListener{
            finish()
//            val dbhelper=comDBhelper(this,"comment2",1)
//            val db=dbhelper.writableDatabase
//            val cursor=db.query("comment2",null,null,null,null,null,null)
//            if (cursor.moveToFirst()){
//                val id=cursor.getInt(cursor.getColumnIndex("id"))
//                val username=cursor.getInt(cursor.getColumnIndex("username"))
//                val content=cursor.getInt(cursor.getColumnIndex("content"))
//                Log.d("inputcomment","id是$id 名字是$username 评论是$content")
//            }
//            cursor.close()

        }
    }
}