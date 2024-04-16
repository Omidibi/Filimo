package com.omid.filimo.model

import com.google.gson.annotations.SerializedName

data class SingleVideoModel(

    @SerializedName("ALL_IN_ONE_VIDEO")
    val singleVideo: List<SingleVideo>
)