package com.example.netclankotlin.network

import com.example.netclankotlin.exploreResponse.ExploreResponse
import com.example.netclankotlin.otp.GetOtpResponse
import com.example.netclankotlin.otp.VerifyOtpResponse
import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import java.util.*

interface ApiInterface {

    @Headers("Content-Type: application/json")
    @POST("user/getOtp")
    fun getOtp(
        @Body body: JsonObject?
    ): Call<GetOtpResponse>?

    @Headers("Content-Type: application/json")
    @POST("user/verifyOtp")
    fun verifyOtp(@Body body: JsonObject?): Call<VerifyOtpResponse>?
//
//    @Headers("Content-Type: application/json")
//    @GET("user/explore/getExplore/type/0")
//    suspend fun getExplore(@Query("page") page: Int): Response<ExploreResponse>?


    @Headers("Content-Type: application/json")
    @GET("user/explore/getExplore/type/0")
     fun getExplore(@Query("page") page: Int): Observable<ExploreResponse>
}