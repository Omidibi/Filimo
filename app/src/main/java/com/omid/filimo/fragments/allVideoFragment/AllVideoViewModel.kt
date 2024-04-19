package com.omid.filimo.fragments.allVideoFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.omid.filimo.api.WebServiceCaller
import com.omid.filimo.model.AllVideoModel
import com.omid.filimo.utils.configuration.AppConfiguration
import com.omid.filimo.utils.internetLiveData.CheckNetworkConnection
import com.omid.filimo.utils.networkAvailable.NetworkAvailable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AllVideoViewModel(application: Application) : AndroidViewModel(application) {

    private val webServiceCaller = WebServiceCaller()
    val checkNetworkConnection = CheckNetworkConnection(application)
    val allVideoModel = MutableLiveData<AllVideoModel>()

    init {
        checkNetworkConnection.observeForever { isConnected ->
            if (isConnected) {
                getAllVideo()
            }
        }
    }

    fun getAllVideo(){
        if (checkNetworkConnection.value == true){
            CoroutineScope(Dispatchers.IO).launch{
                webServiceCaller.getAllVideo().apply {
                    allVideoModel.postValue(this)
                }
            }
        }
    }

    fun networkAvailable(): Boolean {
        return NetworkAvailable.isNetworkAvailable(AppConfiguration.getContext())
    }
}