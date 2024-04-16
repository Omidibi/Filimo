package com.omid.filimo.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserComment(
    @SerializedName("comment_text")
    val commentText: String,
    @SerializedName("user_name")
    val userName: String,
    @SerializedName("video_id")
    val videoId: String
) : Parcelable