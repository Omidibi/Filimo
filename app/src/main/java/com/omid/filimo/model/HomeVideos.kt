package com.omid.filimo.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class HomeVideos(
    @SerializedName("ALL_IN_ONE_VIDEO")
    val allInOneVideo: AllInOneVideo
) : Parcelable