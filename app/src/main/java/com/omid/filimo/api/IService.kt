package com.omid.filimo.api

import com.omid.filimo.model.models.BannerModel
import com.omid.filimo.model.models.CatListByIdModel
import com.omid.filimo.model.models.CategoryModel
import com.omid.filimo.model.models.HomeVideos
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IService {

    @GET("api.php?home_banner")
    fun banner(): Call<BannerModel>

    @GET("api.php?home_videos")
    fun homeVideo(): Call<HomeVideos>

    @GET("api.php?cat_list")
    fun categories(): Call<CategoryModel>

    @GET("api.php?")
    fun videoListByCatId(@Query("cat_id") id: String): Call<CatListByIdModel>

}