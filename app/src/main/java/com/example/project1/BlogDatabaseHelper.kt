package com.example.project1

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BlogDatabaseHelper(val context: Context, name: String, version: Int) : SQLiteOpenHelper(context, name, null, version) {
    private val createBlog = "create table Blog (" +
            "id integer primary key autoincrement," +
            "blogtitle text," +
            "blogcontent text,"+"writer text)"
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createBlog)

    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {   }
}