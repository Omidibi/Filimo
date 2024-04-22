package com.omid.filimo.model

import com.google.gson.annotations.SerializedName

data class CommentModel(
    @SerializedName("ALL_IN_ONE_VIDEO")
    val comment: List<Comment>
)