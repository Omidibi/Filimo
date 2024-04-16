package com.omid.filimo.api

object InitIService {

    val iService = ApiRetrofit.retrofit.create(IService::class.java)
}