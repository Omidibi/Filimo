package com.omid.filimo.fragments.bookmarkFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.omid.filimo.db.RoomDBInstance
import com.omid.filimo.model.VideoBookmark
import com.omid.filimo.utils.configuration.AppConfiguration
import com.omid.filimo.utils.internetLiveData.CheckNetworkConnection
import com.omid.filimo.utils.networkAvailable.NetworkAvailable

class BookmarkViewModel(application: Application) : AndroidViewModel(application) {

    val checkNetworkConnection = CheckNetworkConnection(application)

    fun networkAvailable(): Boolean {
        return NetworkAvailable.isNetworkAvailable(AppConfiguration.getContext())
    }

    fun showAllBookmark(): MutableList<VideoBookmark> {
        return RoomDBInstance.roomDbInstance.dao().showAllBookmark()
    }

    fun isEmptyBookmark(): Boolean {
        return RoomDBInstance.roomDbInstance.dao().showAllBookmark().isEmpty()
    }
}