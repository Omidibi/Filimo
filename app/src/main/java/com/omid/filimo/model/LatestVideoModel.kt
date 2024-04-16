package com.omid.filimo.model

import com.google.gson.annotations.SerializedName

data class LatestVideoModel(

    @SerializedName("ALL_IN_ONE_VIDEO")
    val latestVideo: List<LatestVideo>
)