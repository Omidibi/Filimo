package com.omid.filimo.ui.dashboard.showCase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.omid.filimo.api.WebServiceCaller
import com.omid.filimo.model.BannerModel
import com.omid.filimo.model.HomeVideos
import com.omid.filimo.utils.configuration.AppConfiguration
import com.omid.filimo.utils.internetLiveData.CheckNetworkConnection
import com.omid.filimo.utils.networkAvailable.NetworkAvailable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShowCaseViewModel(application: Application) : AndroidViewModel(application) {

    private val webServiceCaller = WebServiceCaller()
    val checkNetworkConnection = CheckNetworkConnection(application)
    val banner = MutableLiveData<BannerModel>()
    val homeVideos = MutableLiveData<HomeVideos>()

    init {
        checkNetworkConnection.observeForever { isConnected ->
            if (isConnected) {
                getBanner()
                getHomeVideos()
            }
        }
    }

    fun getBanner() {
        if (checkNetworkConnection.value == true) {
            CoroutineScope(Dispatchers.IO).launch {
                webServiceCaller.getBanner().apply {
                    banner.postValue(this)
                }
            }
        }
    }

    fun getHomeVideos() {
        if (checkNetworkConnection.value == true) {
            CoroutineScope(Dispatchers.IO).launch {
                webServiceCaller.getHomeVideo().apply {
                    homeVideos.postValue(this)
                }
            }
        }
    }

    fun networkAvailable(): Boolean {
        return NetworkAvailable.isNetworkAvailable(AppConfiguration.getContext())
    }
}