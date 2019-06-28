package com.example.tarlad.helpers

import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.net.URI
import android.R.string
import android.os.AsyncTask.execute
import okhttp3.EventListener
import kotlin.concurrent.thread


class ServerConnector{
    val API = "https://sqltestvlad.herokuapp.com/api/"
    open val REGISTER = "${API}register"

    public fun getRequest(url:String, values: Map<String, String>?, listener: DownloadListener): String?{
        var stringValues = "?"
        values?.entries?.forEach { e -> stringValues+="${e.key}=${e.value}&"}
        stringValues=stringValues.substring(0, stringValues.length-1)
        println(stringValues)
        val client = OkHttpClient()
        println(url+stringValues)
        val request = Request.Builder().url(url+stringValues).build()
        thread {
            val body = client.newCall(request).execute().body
            if (body != null) {
                listener.onDownloaded(body.string())
            } else {
                listener.onError("")
            }
        }
        return null
    }

    interface DownloadListener{
        fun onError(error: String)
        fun onDownloaded(result: String?)
    }
}