package com.example.samplesmslocal

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

open class MessageModel(

    @PrimaryKey open var id : String = UUID.randomUUID().toString(),

    open var type : Int = 0,

    @Required open var name : String = "",

    open var message : String = "",

    open var time : String = ""
) : RealmObject()