package com.omid.filimo.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AllInOneVideo(
    @SerializedName("all_video")
    val video: List<Video>,
    @SerializedName("category")
    val category: List<Category>,
    @SerializedName("featured_video")
    val featuredVideo: List<Video>,
    @SerializedName("latest_video")
    val latestVideo: List<Video>
) : Parcelable