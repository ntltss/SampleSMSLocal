package com.example.samplesmslocal

import android.app.DownloadManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.format.DateUtils
//import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.type.Date
import com.google.type.DateTime
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.temp.*
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import java.net.URL
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Frag02 : Fragment() {

    fun onCreateView(context: Context){

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag02_layout, container, false)
    }



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Toast.makeText(context , "トーストメッセージ2", Toast.LENGTH_LONG).show();

        /*************** DB関連の処理 ****************************/
        Realm.init(getActivity()) // DBの初期化

        //　DBの列を変更しても落ちないように設定
        val realmConfig = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()

        //val mRealm = Realm.getDefaultInstance() // realmのインスタンスを取得
        val mRealm = Realm.getInstance(realmConfig)

        // 時間の設定
        val dateAndtime: LocalDateTime = LocalDateTime.now()
        val onlyDate: LocalDate = LocalDate.now()
        var formatter = DateTimeFormatter.ofPattern("HH:mm")
        var formattedTime = dateAndtime.format(formatter)

        println("Current date and time: $dateAndtime")
        println("Current date and time: $formattedTime")
        println("Current date: $onlyDate")

        // テーブルの件数
        val countAll = mRealm.where(MessageModel::class.java).findAll()
        println(countAll.count())
        println(countAll+1)
        println(countAll.count()+1)
        // 主キーの値の設定
        val primaryKeyValue = (countAll.count()+1).toString()
        //  val primaryKeyValue = "37"

        // DBにデータを追加
        mRealm.executeTransaction { realm ->
            //val obj = realm.createObject(SampleModel::class.java!!)
            val obj = realm.createObject(MessageModel::class.java,primaryKeyValue)
            //val obj = realm.createObject(MessageModel::class.java,"564")
            obj.type = 0
            obj.name = "test+$primaryKeyValue"
            obj.message = "testMessage+$primaryKeyValue"
            obj.time = formattedTime
        }
//       // DBに入っているSampleModelのうち、1件のレコードを取得
//        val model = mRealm.where(MessageModel::class.java).equalTo("id",primaryKeyValue).findFirst()
//
//        // 取得したデータから値を取り出す
//        val id = model?.id
//        val name = model?.name
//        val message = model?.message
//        val time = model?.time

//        // 画面に表示
//        val toast = Toast.makeText(getActivity(), "送信時刻: "+ time, Toast.LENGTH_LONG)
//        toast.show()


        /*************** DB内容のリセット処理(削除する場合はコメントアウトを解除して実行)**********/
//        val deleteDB = mRealm.where(MessageModel::class.java).findAll()
//        mRealm.executeTransaction {
//            deleteDB.deleteAllFromRealm()
//        }

        /*************** チャット表示関連の処理 ***************************************/
        //リストの表示
        lateinit var simpleRecyclerView: RecyclerView

        // DB内容を全て取得
        val model = mRealm.where(MessageModel::class.java).findAll()

        // 表示するデータを作成し、Adapterに渡す
        //val list2 = Array<MyData_Talk>(5) { MyData_Talk("名前$it", "メッセージ$it","日付$it") }
        //val list2 = Array<MyData_Talk>(5) { MyData_Talk("id$it",0,"名前$it", "メッセージ$it","時間$it") }
        //val list2 = Array<MyData_Talk>(3) { MyData_Talk("id$it","名前$it", "メッセージ$it","時間$it") }

        // DB内容の設定内容確認用
//        for(item in model){
//            println(item.id)
//            println(item.name)
//            println(item.message)
//            println(item.time)
//        }

        // DBの内容をリストに設定
        val mList = mutableListOf<MyData_Talk>()
        for((index,item) in model.withIndex()){
            mList.add(index,MyData_Talk(item.id,item.type,item.name,item.message,item.time))
//            println(mList)
        }
//        for(temp in mList){
//            println(temp.type)
//            println(temp.name)
//            println(temp.message)
//            println(temp.time)
//        }

//        list2[0].name = "田中 勝"
//        list2[0].message = "message0"
//        list2[0].time = "12:14"
//        list2[1].name = "高橋 大輔"
//        list2[1].message = "message1"
//        list2[1].time = "12:14"
//        list2[2].name = "山田 太郎"
//        list2[2].time = "14:15"
//        list2[2].message = "message2"
//        list2[3].name = "鈴木 真一"
//        list2[3].message = "message3"
//        list2[3].time = "09:19"
//        list2[4].name = "佐藤 太一"
//        list2[4].message = "message4"
//        list2[4].time = "06:49"

//        val adapter = MyViewAdapter_Talk(list2)
        val adapter = MyViewAdapter_Talk(mList)
        val layoutManager = LinearLayoutManager(getActivity())

        // アダプターとレイアウトマネージャーをセット
        simpleRecyclerView = view.findViewById(R.id.my_recycler_view2)
        simpleRecyclerView.layoutManager = layoutManager
        simpleRecyclerView.adapter = adapter

        // 最下行のメッセージまでスクロール
        // simpleRecyclerView.smoothScrollToPosition(model.count())
        val underPosition = mList.count()
        simpleRecyclerView.scrollToPosition(underPosition-1)

        // メッセージ入力内容の送信箇所の実装
        val editText = view.findViewById<EditText>(R.id.editTextMessage)
        val button = view.findViewById<Button>(R.id.buttonSend)
        button.setOnClickListener {
            //Toast.makeText(getActivity(), editText.text.toString(), Toast.LENGTH_SHORT).show()
            Realm.init(getActivity()) // DBの初期化

            //　DBの列を変更しても落ちないように設定
            val realmConfig = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build()

            //val mRealm = Realm.getDefaultInstance() // realmのインスタンスを取得
            val mRealm = Realm.getInstance(realmConfig)
            // DBの件数取得

            val mRealmUpdate = mRealm.where(MessageModel::class.java).findAll()
            val DB_COUNT = mRealmUpdate.count()
            val nextPrimaryKey = DB_COUNT+1
            Toast.makeText(getActivity(), nextPrimaryKey.toString(), Toast.LENGTH_SHORT).show()

            // 時間の設定
            val dateAndtime: LocalDateTime = LocalDateTime.now()
            val onlyDate: LocalDate = LocalDate.now()
            var formatter = DateTimeFormatter.ofPattern("HH:mm")
            var formattedTime = dateAndtime.format(formatter)

            // DBにデータを追加
            mRealm.executeTransaction { realm ->
                //val obj = realm.createObject(SampleModel::class.java!!)
                val obj = realm.createObject(MessageModel::class.java,nextPrimaryKey.toString())
                obj.type = 0
                obj.name = "test+$DB_COUNT"
                obj.message = view.findViewById<EditText>(R.id.editTextMessage).text.toString()
                obj.time = formattedTime
            }

            // DBの内容をリストに設定
            val mList = mutableListOf<MyData_Talk>()
            for((index,item) in model.withIndex()){
                mList.add(index,MyData_Talk(item.id,item.type,item.name,item.message,item.time))
//            println(mList)
            }
            for(temp in mList){
                println(temp.id)
                println(temp.type)
                println(temp.name)
                println(temp.message)
                println(temp.time)
            }

            val adapter = MyViewAdapter_Talk(mList)
            val layoutManager = LinearLayoutManager(getActivity())

            // アダプターとレイアウトマネージャーをセット
            simpleRecyclerView = view.findViewById(R.id.my_recycler_view2)
            simpleRecyclerView.layoutManager = layoutManager
            simpleRecyclerView.adapter = adapter

            // 最下行のメッセージまでスクロール
//            simpleRecyclerView.smoothScrollToPosition(model.count())
            simpleRecyclerView.scrollToPosition(DB_COUNT)

            // okHttpを使ってfirebaseへリクエストを送る処理
//            var httpClient = HttpClient()
//            var success = httpClient.postMessage("test")
            //println("送信してみた結果:$success")

        }
    }


}

//class Frag02 : Fragment() {
//    @Nullable
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        @Nullable container: ViewGroup?,
//        @Nullable savedInstanceState: Bundle?
//    ): View {
//        return inflater.inflate(R.layout.frag02_layout, container, false)
//    }
//}