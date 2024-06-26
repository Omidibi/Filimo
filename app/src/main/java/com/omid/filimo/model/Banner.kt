package com.omid.filimo.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Banner(
    @SerializedName("banner_image")
    val bannerImage: String,
    @SerializedName("banner_image_thumb")
    val bannerImageThumb: String,
    @SerializedName("banner_name")
    val bannerName: String,
    @SerializedName("banner_url")
    val bannerUrl: String,
    @SerializedName("id")
    val id: String
) : Parcelable