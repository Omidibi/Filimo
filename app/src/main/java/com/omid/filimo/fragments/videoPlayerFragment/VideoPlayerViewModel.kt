package com.omid.filimo.fragments.videoPlayerFragment

import android.app.Application
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.bumptech.glide.Glide
import com.omid.filimo.R
import com.omid.filimo.api.WebServiceCaller
import com.omid.filimo.db.RoomDBInstance
import com.omid.filimo.model.CommentModel
import com.omid.filimo.model.SingleVideoModel
import com.omid.filimo.model.Video
import com.omid.filimo.model.VideoBookmark
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

    fun getComment(commentText: String,userName: String,postId: String): LiveData<CommentModel>{
        webServiceCaller.getComment(commentText, userName, postId)
        return commentModel
    }

    fun networkAvailable(): Boolean {
        return NetworkAvailable.isNetworkAvailable(AppConfiguration.getContext())
    }

    fun insertViewed(video: Video, id: String){
        if (RoomDBInstance.roomDbInstance.dao().searchByIdViewed(id).isEmpty()){
            RoomDBInstance.roomDbInstance.dao().insertViewed(video)
        }
    }

    fun isBookmarkEmpty(id: String): Boolean{
        return RoomDBInstance.roomDbInstance.dao().searchByIdBookmark(id).isEmpty()
    }

    fun insertBookmark(videoBookmark: VideoBookmark,id: String,imgBookmark: AppCompatImageView){
        if (RoomDBInstance.roomDbInstance.dao().searchByIdBookmark(id).isEmpty()){
            RoomDBInstance.roomDbInstance.dao().insertBookmark(videoBookmark)
            Glide.with(AppConfiguration.getContext()).load(R.drawable.bookmark).into(imgBookmark)
        } else {
            RoomDBInstance.roomDbInstance.dao().deleteBookmark(id)
            Glide.with(AppConfiguration.getContext()).load(R.drawable.not_bookmark).into(imgBookmark)
        }
    }
}