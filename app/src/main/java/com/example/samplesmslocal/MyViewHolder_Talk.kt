package com.example.samplesmslocal

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyViewHolder_Talk(private val view: View) : RecyclerView.ViewHolder(view) {
    //val id: TextView = view.findViewById(R.id.id)
    //val type: TextView = view.findViewById(R.id.type)
    val name: TextView = view.findViewById(R.id.name)
    val message: TextView = view.findViewById(R.id.message)
    //val date: TextView = view.findViewById(R.id.date)
    val time: TextView = view.findViewById(R.id.time)
}
class MyViewHolder_Talk_Send(private val view: View) : RecyclerView.ViewHolder(view) {
    //val id: TextView = view.findViewById(R.id.id)
    //val type: TextView = view.findViewById(R.id.type)
    val time: TextView = view.findViewById(R.id.time)
    //val name: TextView = view.findViewById(R.id.name)
    val message: TextView = view.findViewById(R.id.message)
    //val date: TextView = view.findViewById(R.id.date)

}