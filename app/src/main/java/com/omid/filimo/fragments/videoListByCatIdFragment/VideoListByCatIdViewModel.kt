package com.omid.filimo.fragments.videoListByCatIdFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.omid.filimo.api.WebServiceCaller
import com.omid.filimo.model.CatListByIdModel
import com.omid.filimo.utils.configuration.AppConfiguration
import com.omid.filimo.utils.internetLiveData.CheckNetworkConnection
import com.omid.filimo.utils.networkAvailable.NetworkAvailable

class VideoListByCatIdViewModel(application: Application) : AndroidViewModel(application) {

    private val webServiceCaller = WebServiceCaller()
    val checkNetworkConnection = CheckNetworkConnection(application)
    private val catListByIdModel: LiveData<CatListByIdModel> = webServiceCaller.catListByIdModel

    fun getVideoListByCatId(catId: String): LiveData<CatListByIdModel> {
        webServiceCaller.getVideoListByCatId(catId)
        return catListByIdModel
    }

    fun networkAvailable(): Boolean {
        return NetworkAvailable.isNetworkAvailable(AppConfiguration.getContext())
    }
}