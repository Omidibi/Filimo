package com.omid.filimo.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SingleVideo(
    @SerializedName("cat_id")
    val catId: String,
    @SerializedName("category_name")
    val categoryName: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("rate_avg")
    val rateAvg: String,
    @SerializedName("related")
    val related: List<Related>,
    @SerializedName("totel_viewer")
    val totalViewer: String,
    @SerializedName("user_comments")
    val userComments: List<UserComment>,
    @SerializedName("video_description")
    val videoDescription: String,
    @SerializedName("video_duration")
    val videoDuration: String,
    @SerializedName("video_id")
    val videoId: String,
    @SerializedName("video_thumbnail_b")
    val videoThumbnailB: String,
    @SerializedName("video_thumbnail_s")
    val videoThumbnailS: String,
    @SerializedName("video_title")
    val videoTitle: String,
    @SerializedName("video_type")
    val videoType: String,
    @SerializedName("video_url")
    val videoUrl: String
) : Parcelable