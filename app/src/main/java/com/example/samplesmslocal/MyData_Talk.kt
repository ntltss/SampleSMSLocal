package com.example.samplesmslocal

import java.util.*

class MyData_Talk {
    //    constructor(name: String, message: String,date: String){
    constructor(id: String,type:Int,name:String,message: String,time: String){
        this.id = id
        this.type = type
        this.name = name
        this.message = message
        this.time = time
        //this.time = time
    }
    var id:String = "id"
    var type:Int = 1
    var name: String = "名前"
    var message: String = "メッセージ"
    var time: String = "日付"
//    var time: String = "時間"


}