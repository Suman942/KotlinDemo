package com.example.netclankotlin.network

import com.google.gson.JsonObject

 class RequestFormatter {

     companion object {

         fun jsonObjectGetOtp(phone: String?, countryCode: String?, userType: Int): JsonObject? {
             val jsonObject = JsonObject()
             jsonObject.addProperty("phone", phone)
             jsonObject.addProperty("countryCode", countryCode)
             jsonObject.addProperty("userType", userType)
             return jsonObject
         }

         fun jsonObjectVerifyOtp(phone: String?,countryCode: String?,sessionId:String?):JsonObject?{
                val  jsonObject = JsonObject()
             jsonObject.addProperty("phone", phone)
             jsonObject.addProperty("countryCode", countryCode)
             jsonObject.addProperty("userType", 0)
             jsonObject.addProperty("sessionId", sessionId)
             jsonObject.addProperty("otp","1234")
             jsonObject.addProperty("skipValidation",true)
//             jsonObject.addProperty("password",123456789)
             return jsonObject
         }

//         fun jsonObjectVerifyOtp(
//             phone: String?,
//             countryCode: String?,
//             userType: Int,
//             sessionId: String?,
//             otp: String?,
//             socialId: String?,
//             skipValidation: Boolean
//         ): JsonObject? {
//             val jsonObject = JsonObject()
//             jsonObject.addProperty("phone", phone)
//             jsonObject.addProperty("countryCode", countryCode)
//             jsonObject.addProperty("userType", userType)
//             jsonObject.addProperty("sessionId", sessionId)
//             jsonObject.addProperty("otp", otp)
//             jsonObject.addProperty("skipValidation", skipValidation)
//             return jsonObject
//         }

     }



}