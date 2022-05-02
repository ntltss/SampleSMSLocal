package com.example.samplesmslocal

import android.content.Context
import android.os.Bundle
//import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class Frag01 : Fragment() {
    fun onCreateView(context: Context){

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag01_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(context , "トーストメッセージ", Toast.LENGTH_LONG).show();

        //リストの表示
        lateinit var simpleRecyclerView: RecyclerView

        // 表示するデータを作成し、Adapterに渡す
//        val list = Array<MyData>(10) { MyData("タイトル$it", "メッセージ$it","日付$it") }
        val list = Array<MyData>(5) { MyData("名前$it","日付$it") }
        list[0].name = "田中 勝"
        list[0].date = "12:14"
        list[1].name = "高橋 大輔"
        list[1].date = "12:14"
        list[2].name = "山田 太郎"
        list[2].date = "14:15"
        list[3].name = "鈴木 真一"
        list[3].date = "09:19"
        list[4].name = "佐藤 太一"
        list[4].date = "06:49"

        val adapter = MyViewAdapter(list)
        val layoutManager = LinearLayoutManager(getActivity())

        // アダプターとレイアウトマネージャーをセット
        simpleRecyclerView = view.findViewById(R.id.my_recycler_view)
        simpleRecyclerView.layoutManager = layoutManager
        simpleRecyclerView.adapter = adapter
    }

}

//class Frag01 : Fragment() {
//    @Nullable
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        @Nullable container: ViewGroup?,
//        @Nullable savedInstanceState: Bundle?
//
//    ): View {
//        super.onCreateView(inflater, container, savedInstanceState)
//
//        //ログイン名の取得
//        val myApp = MyApp.getInstance()
//        val QRResult = myApp.QRResult
//        Toast.makeText(context , QRResult, Toast.LENGTH_LONG).show();
//        //Toast.makeText(context , "トーストメッセージ", Toast.LENGTH_LONG).show();
//
//        return inflater.inflate(R.layout.frag01_layout, container, false)
//        return inflater.inflate(R.layout.frag01_layout, container, false)
//    }
//}