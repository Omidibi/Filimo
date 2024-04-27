package com.omid.filimo.fragments.viewedFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.omid.filimo.db.RoomDBInstance
import com.omid.filimo.model.Video
import com.omid.filimo.utils.configuration.AppConfiguration
import com.omid.filimo.utils.internetLiveData.CheckNetworkConnection
import com.omid.filimo.utils.networkAvailable.NetworkAvailable

class ViewedViewModel(application: Application) : AndroidViewModel(application) {

    val checkNetworkConnection = CheckNetworkConnection(application)

    fun networkAvailable(): Boolean {
        return NetworkAvailable.isNetworkAvailable(AppConfiguration.getContext())
    }

    fun showAllViewed(): MutableList<Video> {
        return RoomDBInstance.roomDbInstance.dao().showAllViewed()
    }
}