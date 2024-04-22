package com.omid.filimo.model

import com.google.gson.annotations.SerializedName

data class RatingModel(
    @SerializedName("ALL_IN_ONE_VIDEO")
    val rating: List<Rating>
)