package com.omid.filimo.model.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CatListByIdModel(
    @SerializedName("ALL_IN_ONE_VIDEO")
    val categoryList: List<Video>
) : Parcelable