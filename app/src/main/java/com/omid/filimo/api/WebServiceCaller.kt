package com.omid.filimo.api

import com.omid.filimo.model.listener.IListener
import com.omid.filimo.model.models.BannerModel
import com.omid.filimo.model.models.CatListByIdModel
import com.omid.filimo.model.models.CategoryModel
import com.omid.filimo.model.models.HomeVideos
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WebServiceCaller {
    private val iService = ApiRetrofit.retrofit.create(IService::class.java)

    fun getBanners(iListener: IListener<BannerModel>) {
        iService.banner().enqueue(object : Callback<BannerModel> {
            override fun onResponse(call: Call<BannerModel>, response: Response<BannerModel>) {
                iListener.onSuccess(call,response.body()!!)
            }

            override fun onFailure(call: Call<BannerModel>, t: Throwable) {
                iListener.onFailure(call,t,"Error")
            }

        })
    }

    fun getHomeVideo(iListener: IListener<HomeVideos>) {
        iService.homeVideo().enqueue(object : Callback<HomeVideos> {
            override fun onResponse(call: Call<HomeVideos>, response: Response<HomeVideos>) {
                iListener.onSuccess(call,response.body()!!)
            }

            override fun onFailure(call: Call<HomeVideos>, t: Throwable) {
                iListener.onFailure(call,t,"Error")
            }

        })
    }

    fun getCategories(iListener: IListener<CategoryModel>) {
        iService.categories().enqueue(object : Callback<CategoryModel> {
            override fun onResponse(call: Call<CategoryModel>, response: Response<CategoryModel>) {
                iListener.onSuccess(call,response.body()!!)
            }

            override fun onFailure(call: Call<CategoryModel>, t: Throwable) {
                iListener.onFailure(call,t,"Error")
            }

        })
    }

    fun getVideoListByCatId(id: String, iListener: IListener<CatListByIdModel>) {
        iService.videoListByCatId(id).enqueue(object : Callback<CatListByIdModel> {
            override fun onResponse(call: Call<CatListByIdModel>, response: Response<CatListByIdModel>) {
                iListener.onSuccess(call,response.body()!!)
            }

            override fun onFailure(call: Call<CatListByIdModel>, t: Throwable) {
                iListener.onFailure(call,t,"Error")
            }

        })
    }
}