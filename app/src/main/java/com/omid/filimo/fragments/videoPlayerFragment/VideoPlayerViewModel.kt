package com.omid.filimo.fragments.videoPlayerFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.omid.filimo.api.WebServiceCaller
import com.omid.filimo.model.CommentModel
import com.omid.filimo.model.SingleVideoModel
import com.omid.filimo.utils.configuration.AppConfiguration
import com.omid.filimo.utils.internetLiveData.CheckNetworkConnection
import com.omid.filimo.utils.networkAvailable.NetworkAvailable

class VideoPlayerViewModel(application: Application) : AndroidViewModel(application) {

    private val webServiceCaller = WebServiceCaller()
    private val singleVideoModel: LiveData<SingleVideoModel> = webServiceCaller.singleVideoModel
    private val commentModel: LiveData<CommentModel> = webServiceCaller.commentModel
    val checkNetworkConnection = CheckNetworkConnection(application)

    fun getSingleVideo(videoId: String): LiveData<SingleVideoModel>{
        webServiceCaller.getSingleVideo(videoId)
        return singleVideoModel
    }

    fun getComment(commentText: String,userName: String): LiveData<CommentModel>{
        webServiceCaller.getComment(commentText, userName)
        return commentModel
    }

    fun networkAvailable(): Boolean {
        return NetworkAvailable.isNetworkAvailable(AppConfiguration.getContext())
    }
}