package com.example.samplesmslocal

import android.app.Application
class MyApp :Application(){
    var QRResult: String? = null

    companion object {
        private var instance : MyApp? = null

        fun  getInstance(): MyApp {
            if (instance == null)
                instance = MyApp()

            return instance!!
        }
    }
}