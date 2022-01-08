package com.example.project1

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class comDBhelper(val context: Context, name: String, version: Int):
    SQLiteOpenHelper(context, name, null, version){

    private val createDaily = "create table comment2 (" +
            "id integer," +
            "username text,"+
            "content text)"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createDaily)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }
}