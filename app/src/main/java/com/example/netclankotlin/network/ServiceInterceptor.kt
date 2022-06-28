package com.example.netclankotlin.network

import com.example.netclankotlin.utils.App
import com.example.netclankotlin.utils.PrefManager
import okhttp3.Interceptor
import okhttp3.Response

class ServiceInterceptor : Interceptor {



    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        var token =    App.context?.let { PrefManager.getInstance(it.applicationContext).authKey }

        if(request.header("No-Authentication")==null){
            //val token = getTokenFromSharedPreference();
            //or use Token Function
            if(!token.isNullOrEmpty())
            {
                request = request.newBuilder()
                    .addHeader("Authorization","Bearer " + token)
                    .build()
            }

        }

        return chain.proceed(request)
    }

}