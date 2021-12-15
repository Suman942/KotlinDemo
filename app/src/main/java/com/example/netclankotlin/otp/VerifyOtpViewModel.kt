package com.example.netclankotlin.otp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.netclankotlin.explore.ExploreResponse
import com.example.netclankotlin.utils.Coroutines
import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class VerifyOtpViewModel : ViewModel() {
    private val verifyOtpRepo = VerifyOtpRepo()
    private val verifyOtpmutableLiveData = MutableLiveData<VerifyOtpResponse>()
    private val getOtpMutableLiveData = MutableLiveData<GetOtpResponse>()
    private val exploreMutableLiveData = MutableLiveData<ExploreResponse>()

    val exploreLiveData: MutableLiveData<ExploreResponse> by lazy {
        exploreMutableLiveData
    }

    fun getExploreData(page: Int) {
        Log.d("exception","out: "+Thread.currentThread())

        viewModelScope.launch(Dispatchers.IO){
            try {
                Log.d("exception","in: "+Thread.currentThread())
                verifyOtpRepo.getInstance()?.getExplore(page, exploreMutableLiveData)
            }
            catch (e: Throwable){
                Log.d("exception",""+e.message)
            }
        }
    }

    val verifyOtpLiveData: MutableLiveData<VerifyOtpResponse> by lazy {
        verifyOtpmutableLiveData
    }

    fun verifyOtpViewModel(jsonObject: JsonObject) {
        verifyOtpRepo.getInstance()?.verifyOtp(jsonObject, verifyOtpmutableLiveData)
    }

    val getOtpLiveData: MutableLiveData<GetOtpResponse> by lazy {
        getOtpMutableLiveData
    }

    fun getOtpViewModel(jsonObject: JsonObject) {
        verifyOtpRepo.getInstance()?.getOtp(jsonObject, getOtpMutableLiveData)
    }
}