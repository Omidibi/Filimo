package com.omid.filimo.api

import com.omid.filimo.model.AllVideoModel
import com.omid.filimo.model.BannerModel
import com.omid.filimo.model.CatListByIdModel
import com.omid.filimo.model.CategoryModel
import com.omid.filimo.model.CommentModel
import com.omid.filimo.model.ForgetPasswordModel
import com.omid.filimo.model.HomeVideos
import com.omid.filimo.model.LatestVideoModel
import com.omid.filimo.model.ProfileUpdateModel
import com.omid.filimo.model.SearchModel
import com.omid.filimo.model.SingleVideoModel
import com.omid.filimo.model.UserLoginModel
import com.omid.filimo.model.UserRegisterModel
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

    @GET("api.php?")
    suspend fun searchVideo(@Query("search_text") search: String): Response<SearchModel>

    @GET("api.php?user_register")
    suspend fun userRegister(
        @Query("name") name: String,
        @Query("email") email: String,
        @Query("password") password: String,
        @Query("phone") phone: String
    ): Response<UserRegisterModel>

    @GET("api.php?users_login")
    fun userLogin(
        @Query("email") email: String,
        @Query("password") password: String
    ):Observable<UserLoginModel>

    @GET("api.php?user_profile_update")
    fun userProfileUpdate(
        @Query("user_id") userId: String,
        @Query("name") name: String,
        @Query("email") email: String,
        @Query("password") password: String,
        @Query("phone") phone: String
    ): Observable<ProfileUpdateModel>

    @GET("api.php?forgot_pass")
    fun forgetPassword(@Query("email") email: String): Observable<ForgetPasswordModel>

    @GET("api.php?video_comment")
    fun comment(
        @Query("comment_text") commentText: String,
        @Query("user_name") userName: String,
        @Query("post_id") postId: String
    ): Observable<CommentModel>
}