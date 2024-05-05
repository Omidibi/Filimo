package com.omid.filimo.api

object InitIService {

    val iService: IService = ApiRetrofit.retrofit.create(IService::class.java)
}