package com.omid.filimo.api

import com.omid.filimo.model.AllVideoModel
import com.omid.filimo.model.BannerModel
import com.omid.filimo.model.CatListByIdModel
import com.omid.filimo.model.CategoryModel
import com.omid.filimo.model.HomeVideos
import com.omid.filimo.model.LatestVideoModel
import com.omid.filimo.model.SingleVideoModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IService {

    @GET("api.php?home_banner")
    suspend fun banner(): Response<BannerModel>

    @GET("api.php?home_videos")
    suspend fun homeVideo(): Response<HomeVideos>

    @GET("api.php?All_videos")
    suspend fun allVideo(): Response<AllVideoModel>

    @GET("api.php?latest_video")
    fun latestVideo(): Observable<LatestVideoModel>

    @GET("api.php?cat_list")
    fun categories(): Observable<CategoryModel>

    @GET("api.php?")
    fun videoListByCatId(@Query("cat_id") catId: String): Observable<CatListByIdModel>

    @POST("api.php?")
    fun singleVideo(@Query("video_id") videoId: String): Observable<SingleVideoModel>
}