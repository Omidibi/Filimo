package com.omid.filimo.fragments.videoPlayerFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.omid.filimo.api.WebServiceCaller
import com.omid.filimo.model.SingleVideoModel
import com.omid.filimo.utils.configuration.AppConfiguration
import com.omid.filimo.utils.internetLiveData.CheckNetworkConnection
import com.omid.filimo.utils.networkAvailable.NetworkAvailable

class VideoPlayerViewModel(application: Application) : AndroidViewModel(application) {

    private val webServiceCaller = WebServiceCaller()
    private val singleVideoModel: LiveData<SingleVideoModel> = webServiceCaller.singleVideoModel
    val checkNetworkConnection = CheckNetworkConnection(application)

    fun getSingleVideo(videoId: String): LiveData<SingleVideoModel>{
        webServiceCaller.getSingleVideo(videoId)
        return singleVideoModel
    }

    fun networkAvailable(): Boolean {
        return NetworkAvailable.isNetworkAvailable(AppConfiguration.getContext())
    }
}