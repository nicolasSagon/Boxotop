package com.sagon.boxotop.data.repository

import com.sagon.boxotop.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstance {

    val retrofit : Retrofit

    val omdbWebService : OmdbWebService

    //TODO Remove that from this file and find a way to add it automatically
    val apiKey = "fb06540e"

    init {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

        retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.OMDB_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        omdbWebService = retrofit.create(OmdbWebService::class.java)
    }

}