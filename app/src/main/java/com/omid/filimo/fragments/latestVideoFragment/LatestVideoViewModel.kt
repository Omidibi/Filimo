package com.omid.filimo.fragments.latestVideoFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.omid.filimo.api.WebServiceCaller
import com.omid.filimo.model.LatestVideoModel
import com.omid.filimo.utils.configuration.AppConfiguration
import com.omid.filimo.utils.internetLiveData.CheckNetworkConnection
import com.omid.filimo.utils.networkAvailable.NetworkAvailable

class LatestVideoViewModel(application: Application) : AndroidViewModel(application) {

    private val webServiceCaller = WebServiceCaller()
    val checkNetworkConnection = CheckNetworkConnection(application)
    val latestVideoModel: LiveData<LatestVideoModel> = webServiceCaller.latestVideoModel

    init {
        checkNetworkConnection.observeForever { isConnected ->
            if (isConnected) {
                getLatestVideo()
            }
        }
    }

    fun getLatestVideo(){
        if (checkNetworkConnection.value == true){
            webServiceCaller.getLatestVideo()
        }
    }

    fun networkAvailable(): Boolean {
        return NetworkAvailable.isNetworkAvailable(AppConfiguration.getContext())
    }
}