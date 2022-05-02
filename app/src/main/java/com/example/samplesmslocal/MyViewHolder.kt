package com.example.samplesmslocal

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
//    val title: TextView = view.findViewById(R.id.title)
    val name: TextView = view.findViewById(R.id.name)
    val date: TextView = view.findViewById(R.id.date)
}
