package com.example.netclankotlin.otp

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.netclankotlin.exploreResponse.ExploreResponse
import com.example.netclankotlin.network.ApiClient
import com.example.netclankotlin.network.ApiInterface
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response

object VerifyOtpRepo {
//    var context: Context? = null
    private val TAG = "LoginRepository"
    private var userRepository: VerifyOtpRepo? = null
    private var apiInterface: ApiInterface? = null

    init {
        apiInterface = ApiClient.client?.create(ApiInterface::class.java)
    }
//    fun getInstance(): VerifyOtpRepo? {
//        if (userRepository == null) {
//            userRepository = VerifyOtpRepo()
//        }
//        return userRepository
//    }
//    private fun VerifyOtpRepo(): VerifyOtpRepo? {
////        return userRepository
//    }


    fun getOtp(jsonObject: JsonObject, liveData: MutableLiveData<GetOtpResponse>) {

        apiInterface?.getOtp(jsonObject)?.enqueue(object : retrofit2.Callback<GetOtpResponse> {
            override fun onResponse(
                call: Call<GetOtpResponse>,
                response: Response<GetOtpResponse>
            ) {
                if (response.code() == 200) {
                    Log.d("getOtp", "success")
                    liveData.value = response.body()
                }
                Log.d("getOtp", "code: " + response.code())


            }

            override fun onFailure(call: Call<GetOtpResponse>, t: Throwable) {
                Log.d("getOtp", "error: " + t.message)

            }

        })
    }

    fun verifyOtp(jsonObject: JsonObject, liveData: MutableLiveData<VerifyOtpResponse>) {
        apiInterface?.verifyOtp(jsonObject)
            ?.enqueue(object : retrofit2.Callback<VerifyOtpResponse> {
                override fun onResponse(
                    call: Call<VerifyOtpResponse>,
                    response: Response<VerifyOtpResponse>
                ) {
                    if (response.code() == 200) {
                        Log.d("verifyOtp", "success")
                        var verifyResponse = response.body()
                        val headers = response.headers()
                        var ak = headers.get("ak")
                        if (ak != null) {
                            verifyResponse?.authKey = ak
                        }
                        liveData.value = response.body()
                    }
                    Log.d("verifyOtp", "code: " + response.code())

                }

                override fun onFailure(call: Call<VerifyOtpResponse>, t: Throwable) {
                    Log.d("verifyOtp", "error: " + t.message)

                }
            })
    }

   suspend fun getExplore(page: Int, mutableLiveData: MutableLiveData<ExploreResponse>) {
     val response=  apiInterface?.getExplore(page)
            if (response?.isSuccessful == true){
                mutableLiveData.postValue(response.body())

       }
//        apiInterface?.getExplore(page)?.enqueue(object : retrofit2.Callback<ExploreResponse> {
//            override fun onResponse(
//                call: Call<ExploreResponse>,
//                response: Response<ExploreResponse>
//            ) {
//                if (response.code() == 200){
//                    Log.d("explore", "success")
//                    mutableLiveData.postValue(response.body())
//
//                }
//
//                Log.d("explore", "code: "+response.code())
//
//
//            }
//
//            override fun onFailure(call: Call<ExploreResponse>, t: Throwable) {
//                Log.d("explore", "error: " + t.message)
//
//            }
//        })


    }
}