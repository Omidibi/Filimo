package com.omid.filimo.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiRetrofit {

    val retrofit: Retrofit = Retrofit.Builder().baseUrl("http://mobilemasters.ir/apps/filimo-android/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
}