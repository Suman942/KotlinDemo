package com.example.netclankotlin.utils

import java.io.IOException

import okhttp3.Interceptor.*

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


class TokenRecepter : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Chain): Response {

        //rewrite the request to add bearer token
        val newRequest: Request = chain.request().newBuilder()
            .header("Authorization", "Bearer "+"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiI2MDg5NWQ5ODc2ZTIzMjAwMGFkZDMyZTYiLCJ1c2VyVHlwZSI6MCwiaWF0IjoxNjM4ODYyMTA1LCJpc3MiOiJodHRwczovL25ldGNsYW4uY29tIn0.CXZITxc0SvhFWTdK2CTp1w2c4mBI189Glx-Jbcv3kVg")
            .build()
        return chain.proceed(newRequest)
    }
}