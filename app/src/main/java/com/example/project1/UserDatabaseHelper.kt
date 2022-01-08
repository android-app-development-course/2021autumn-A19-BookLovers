package com.example.project1

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast


class UserDatabaseHelper(val context: Context, name: String, version: Int) : SQLiteOpenHelper(context, name, null, version) {
    private val createUser = "create table user (" +
            "username char(20) PRIMARY KEY not null," +
            "password char(20) not null," +
            "school_name text," +
            "sex char(10)," +
            "age int)"
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createUser)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {   }
}
