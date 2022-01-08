package com.example.project1

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class content_adapter(activity: Activity, private val resourceID:Int, data:List<class_list>)
    :ArrayAdapter<class_list>(activity,resourceID,data) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view=LayoutInflater.from(context).inflate(resourceID,parent,false)
        val username:TextView=view.findViewById(R.id.textView)
        val usercontent:TextView=view.findViewById(R.id.content)
        val item=getItem(position)
        if(item!=null){
            username.setText(item.name)
            usercontent.setText(item.content)
        }
        return view
    }
}