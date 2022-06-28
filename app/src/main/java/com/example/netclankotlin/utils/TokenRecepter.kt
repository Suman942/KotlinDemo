package com.example.netclankotlin.utils

import android.util.Log
import java.io.IOException

import okhttp3.Interceptor.*

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


class TokenRecepter : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Chain): Response {
        var token =    App.context?.let { PrefManager.getInstance(it.applicationContext).authKey }
//        Log.d("Explore","auth: "+ App.context?.let { PrefManager.getInstance(it).authKey } +"\n"+token)
        //rewrite the request to add bearer token
        val newRequest: Request = chain.request().newBuilder()
            .header("Authorization", "Bearer "+token)
            .build()
        return chain.proceed(newRequest)
    }
}