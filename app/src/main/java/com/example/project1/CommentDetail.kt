package com.example.project1

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_comment_detail.*
import kotlinx.android.synthetic.main.activity_main.*

class CommentDetail : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment_detail)
        if (supportActionBar != null){
            supportActionBar?.hide();
        }
        //获取帖子id
        val id=intent.getIntExtra("id",0)
        val username=intent.getStringExtra("username")
        val dbHelper=BlogDatabaseHelper(this,"blog_dh",1)
        val db = dbHelper.writableDatabase;
        val cursor = db.query("Blog", null, null, null, null, null, null)
        if (cursor.moveToFirst()) {
            do {
                // 遍历Cursor对象，取出数据并打印
                    val blogid=cursor.getInt(cursor.getColumnIndex("id"))
                if(blogid==id)
                {
                    val username=cursor.getString(cursor.getColumnIndex("writer"))
                    textView.setText(username)
                    val blogcontent=cursor.getString(cursor.getColumnIndex("blogcontent"))
                    blog.setText(blogcontent)
                    break
                }
            } while (cursor.moveToNext())
        }
        follow_btn_return.setOnClickListener{
            val intent=Intent(this,MainActivity::class.java)
            Toast.makeText(this, "点击了返回", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }
        follow_btn_com.setOnClickListener{
            val intent2=Intent(this,input_comment::class.java)
            intent2.putExtra("id",id)
            intent2.putExtra("username",username)
            startActivity(intent2)
        }
    }

    @SuppressLint("Range")
    override fun onResume(){
        super.onResume()
        val comlist=ArrayList<class_list>()
//        initcom()
        val id = intent.getIntExtra("id", 0)


        //读取数据库显示评论

        val dbhelper=comDBhelper(this,"comment2",1)
        val db = dbhelper.writableDatabase
        val cursor = db.rawQuery("select * from comment2 where id = $id", null)
        if (cursor.moveToFirst()) {
            do {
                val username = cursor.getString(cursor.getColumnIndex("username"))
                val content = cursor.getString(cursor.getColumnIndex("content"))
                comlist.add(class_list(username,content))
            } while (cursor.moveToNext())
        }

        val adapter=content_adapter(this,R.layout.comment_list_item,comlist)
        lsview.adapter=adapter
    }

//    private fun initcom(){
//        repeat(3){
//            comlist.add(class_list("张三","你好罗老师"))
//            comlist.add(class_list("精神病院院长","你好"))
//        }
//    }
}