package com.example.samplesmslocal

import android.app.DownloadManager
import android.telecom.Call
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import okhttp3.*
import java.io.IOException
import javax.security.auth.callback.Callback
import com.google.android.gms.common.api.Response as Response1

class HttpClient : Callback {
    val URL = "https://fcm.googleapis.com/fcm/send"
    val AUTHORIZATION_KEY = "Authorization"
    val AUTHORIZATION_VALUE = "key=AAAAlGgJKvg:APA91bGqsRC67pfpk9HIQz2MlrlDgc9maqCK92fJzSeZWRLCwggNZW9Z9OBoccumY_q0oNoct4nhdPvJB3cc8c4UP8VOoFAsJn02B3RUjR-UzZGWvk03TxbdeiK9mR_XAIQZmTK8rnFQ"


    fun onFailure(call: Call, e: IOException) {
        println("onFailure")
    }
    @Throws(IOException::class)
    fun onResponse(call: Call, response: Response) {
        println("Status code: " + response.code())
        println("Body: " + response.body()!!.string().substring(0, 19) + "...")
    }

    fun postMessage(message: String) {
        // リクエストに付与するため、messageをJSON形式にする
        var messageJSON = "{\"message\" : \"%s\"}".format(message)
        // リクエストの作成
        val request = Request.Builder()
            .url(URL)
            .addHeader(AUTHORIZATION_KEY, AUTHORIZATION_VALUE)
            .post(
                RequestBody.create(
                    MediaType.parse("application/json;charset=utf-8"),
                    messageJSON
                )
            )
            .build()

        // HTTP通信の実行
        //OkHttpClient().newCall(request).enqueue(this)
        OkHttpClient().newCall(request).execute()
    }
}