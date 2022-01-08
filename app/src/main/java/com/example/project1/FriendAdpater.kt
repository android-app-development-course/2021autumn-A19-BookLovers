package com.example.project1

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class FriendAdpater:BaseAdapter {
    var context: Context? = null
    var list1: ArrayList<String>? = null
    var list2: ArrayList<String>? = null
    public constructor(context: Context, list1: ArrayList<String>, list2: ArrayList<String>) {
        this.context = context
        this.list1 = list1
        this.list2 = list2
    }

    override fun getItem(position: Int): Any? {
        return null;
    }

    override fun getItemId(position: Int): Long {

        return position.toLong()

    }

    override fun getCount(): Int {
        return list1!!.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View? = null
        if (convertView == null) {
            view = View.inflate(context, R.layout.list_friend, null)

        } else {
            view = convertView
        }
        val textuser: TextView = view!!.findViewById(R.id.usertext)
        val schooltext: TextView = view!!.findViewById(R.id.schooltext)
        textuser.setText(list1!![position])
        schooltext.setText(list2!![position])

        return view
    }
}