package com.example.samplesmslocal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

internal const val VIEWTYPE_SEND = 0    // 送信、右寄りのレイアウト
internal const val VIEWTYPE_RECV = 1    // 受信、左寄りのレイアウト


//class MyViewAdapter_Talk(private val myDataset: Array<MyData_Talk>) :
class MyViewAdapter_Talk(private val myDataset: MutableList<MyData_Talk>) :
    RecyclerView.Adapter<MyViewHolder_Talk>() {

     override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder_Talk {
        return when(viewType){
            VIEWTYPE_SEND ->{
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.message_talk_send, parent, false)
                return MyViewHolder_Talk(view)
            }
            VIEWTYPE_RECV ->{
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.message_talk, parent, false)
                return MyViewHolder_Talk(view)
            }
            else ->{
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.message_talk_send, parent, false)
                return MyViewHolder_Talk(view)
            }
        }
        // create a new view
//        val view: View = LayoutInflater.from(parent.context)
//            .inflate(R.layout.message_talk, parent, false)
//        return MyViewHolder_Talk(view)

    }
    override fun onBindViewHolder(holder: MyViewHolder_Talk, position: Int) {
        when(getItemViewType(position)) {
            VIEWTYPE_SEND -> {
                //holder.id.text = myDataset[position].id
                //holder.type.text = myDataset[position].type
                //holder.name.text = myDataset[position].name
                holder.message.text = myDataset[position].message
//        holder.date.text = myDataset[position].date
                holder.time.text = myDataset[position].time
            }
            VIEWTYPE_RECV -> {
                //holder.id.text = myDataset[position].id
                //holder.type.text = myDataset[position].type
                holder.name.text = myDataset[position].name
                holder.message.text = myDataset[position].message
//        holder.date.text = myDataset[position].date
                holder.time.text = myDataset[position].time
            }
            else -> {}
        }
//        holder.id.text = myDataset[position].id
//        //holder.type.text = myDataset[position].type
//        holder.name.text = myDataset[position].name
//        holder.message.text = myDataset[position].message
////        holder.date.text = myDataset[position].date
//        holder.time.text = myDataset[position].time
    }
    override fun getItemCount() = myDataset.size

    override fun getItemViewType(position: Int): Int {
        return myDataset[position].type
    }

}