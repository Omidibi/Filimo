package com.omid.filimo.model

import com.google.gson.annotations.SerializedName

data class AllVideoModel(

    @SerializedName("ALL_IN_ONE_VIDEO")
    val allVideo: List<Video>
)