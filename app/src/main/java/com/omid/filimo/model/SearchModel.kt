package com.omid.filimo.model

import com.google.gson.annotations.SerializedName

data class SearchModel(

    @SerializedName("ALL_IN_ONE_VIDEO")
    val search: List<Search>
)