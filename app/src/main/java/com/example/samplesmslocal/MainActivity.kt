package com.example.samplesmslocal

import android.app.DownloadManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody

class MainActivity : AppCompatActivity() {


    companion object {
        const val EXTRA_TEXTDATA = "com.usaco_pg.intentsample.TEXTDATA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        // リストの表示
//        lateinit var simpleRecyclerView: RecyclerView
//
//        // 表示するデータを作成し、Adapterに渡す
//        val list = Array<MyData>(10) { MyData("タイトル$it", "メッセージ$it","日付$it") }
//        val adapter = MyViewAdapter(list)
//        val layoutManager = LinearLayoutManager(this)
//
//        // アダプターとレイアウトマネージャーをセット
//        simpleRecyclerView = findViewById(R.id.my_recycler_view)
//        simpleRecyclerView.layoutManager = layoutManager
//        simpleRecyclerView.adapter = adapter


        // 「はじめる」ボタン押下時に次の画面に遷移する処理
        var button = findViewById<Button>(R.id.button)
        button.setOnClickListener({
//            Toast.makeText(applicationContext , "トーストメッセージ", Toast.LENGTH_LONG).show();

            // intent作成
            val intent = Intent(this, SecondActivity::class.java)

            //名前の設定
            //intent.putExtra(EXTRA_TEXTDATA,button.text.toString())
            intent.putExtra(EXTRA_TEXTDATA,findViewById<EditText>(R.id.editText).text.toString())

            // 画面遷移実行
            startActivity(intent)
        })
    }
}