package com.example.samplesmslocal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MyViewAdapter(private val myDataset: Array<MyData>) :
    RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        // create a new view
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.message, parent, false)
        return MyViewHolder(view)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        holder.title.text = myDataset[position].title
        holder.name.text = myDataset[position].name
        holder.date.text = myDataset[position].date
    }
    override fun getItemCount() = myDataset.size
}