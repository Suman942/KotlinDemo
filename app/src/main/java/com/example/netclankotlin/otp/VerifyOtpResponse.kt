package com.example.netclankotlin.otp

data class VerifyOtpResponse(
    val isNewUser: Boolean,
    val loginProgress: Int,
    val userId: String,
    val userType: Int,
    var authKey : String


)