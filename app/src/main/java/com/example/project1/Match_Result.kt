package com.example.project1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.activity_match_result.*

class Match_Result : AppCompatActivity() {

    private val list1:ArrayList<String> = ArrayList()
    private val list2:ArrayList<String> =ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_result)
        supportActionBar?.hide()
        val get_school=intent.getStringExtra("School")
        val get_age=intent.getStringExtra("Age")?.toInt()

        val get_sex=intent.getStringExtra("Sex")
        val get_name=intent.getStringExtra("Username")

        val dbHelper=UserDatabaseHelper(this,"user_db",1)
        val db = dbHelper.writableDatabase;

        val cursor = db.query("user", null, null, null, null, null, null)

        if (cursor.moveToFirst()) {
            do {
                // 遍历Cursor对象，取出数据并打印
                val school = cursor.getString(cursor.getColumnIndex("school_name"))
                val age = cursor.getInt(cursor.getColumnIndex("age"))
                val sex = cursor.getString(cursor.getColumnIndex("sex"))
                val username=cursor.getString(cursor.getColumnIndex("username"))
                if(school==get_school&&get_age==age&&sex==get_sex&&username!=get_name)
                {

                    list1.add(username)
                    list2.add(school)
                }

            } while (cursor.moveToNext())
        }
        firend_lv.adapter=FriendAdpater(this,list1,list2)
        //主页
        homebtn1.setOnClickListener {

            startActivity(intent)
        }
        //匹配
        matchbtn1.setOnClickListener {
            val intent= Intent(this,Match::class.java)

            startActivity(intent)
        }
        //发布
        postbtn1.setOnClickListener{
            val intent= Intent(this,Post::class.java)
            //传递个人信息，用户名

            startActivity(intent)
        }

        //消息

        //我的
        userbtn1.setOnClickListener {
            val intent= Intent(this,My::class.java)

            startActivity(intent)
        }

    }
}




