package com.example.netclankotlin.network

import com.example.netclankotlin.utils.App
import com.example.netclankotlin.utils.Constants
import com.example.netclankotlin.utils.PrefManager
import com.example.netclankotlin.utils.TokenRecepter
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit


object ApiClient {
    //public static final String BASE_URL = "https://hello-ji-232307.appspot.com/api/";
   private val interceptor = TokenRecepter()

    private  val BASE_URL = Constants.BASEURL
    private val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        .callTimeout(2, TimeUnit.MINUTES)
        .connectTimeout(2, TimeUnit.MINUTES)
        .readTimeout(2, TimeUnit.MINUTES)
        .writeTimeout(2, TimeUnit.MINUTES)
        .retryOnConnectionFailure(false)

    //    .sslSocketFactory(new TLSSocketFactory(),trustManager)
    //    .followRedirects(false)
    //    .followSslRedirects(false)
    //    .cache(null)

    private var retrofit: Retrofit? = null
    @JvmStatic
    val client: Retrofit?
        get() {
            httpClient.addInterceptor(interceptor)
//                @Throws(IOException::class)
//                override fun intercept(chain: Interceptor.Chain): Response {
//                    val newRequest: Request = chain.request().newBuilder()
//                        .addHeader("Authorization",
//                            "Bearer " + App.context?.let { PrefManager.getInstance(it.applicationContext).authKey }
//                        )
//                        .build()
//                    return chain.proceed(newRequest)
//                }
//            })


            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build()
            }
            return retrofit
        }
}