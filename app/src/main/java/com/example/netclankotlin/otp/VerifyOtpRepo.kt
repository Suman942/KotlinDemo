package com.example.netclankotlin.otp

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.netclankotlin.exploreResponse.ExploreResponse
import com.example.netclankotlin.network.ApiClient
import com.example.netclankotlin.network.ApiInterface
import com.google.gson.JsonObject
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit

class VerifyOtpRepo {
    var context: Context? = null
    private val TAG = "LoginRepository"
    private var userRepository: VerifyOtpRepo? = null
    private var apiInterface: ApiInterface? = null

    fun getInstance(): VerifyOtpRepo? {
        if (userRepository == null) {
            userRepository = VerifyOtpRepo()
        }
        return userRepository
    }
//    private fun VerifyOtpRepo(): VerifyOtpRepo? {
//        apiInterface = ApiClient.client?.create(ApiInterface::class.java)
//        return userRepository
//    }


    fun getOtp(jsonObject: JsonObject, liveData: MutableLiveData<GetOtpResponse>) {
        apiInterface = ApiClient.client?.create(ApiInterface::class.java)

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
        apiInterface = ApiClient.client?.create(ApiInterface::class.java)
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

//    suspend fun getExplore(page: Int, mutableLiveData: MutableLiveData<ExploreResponse>) {
//        apiInterface = ApiClient.client?.create(ApiInterface::class.java)
//        val response = apiInterface?.getExplore(page)
//        if (response?.isSuccessful == true) {
//            mutableLiveData.postValue(response.body())
//        }
//    }

    fun getExplore(page: Int,mutableLiveData: MutableLiveData<ExploreResponse>){
        apiInterface = ApiClient.client?.create(ApiInterface::class.java)
        apiInterface?.getExplore(page)?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())?.subscribe(getExploreObserver(mutableLiveData))
    }

    fun getExploreObserver(mutableLiveData: MutableLiveData<ExploreResponse>): Observer<ExploreResponse>{
        return object : Observer<ExploreResponse>{
            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: ExploreResponse) {
                Log.d("Explore","response success")
                mutableLiveData.postValue(t)
            }

            override fun onError(e: Throwable) {
                Log.d("Explore","response error: "+e.message)
                mutableLiveData.postValue(null)
            }

            override fun onComplete() {
            }

        }
    }
}