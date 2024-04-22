package com.omid.filimo.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rating(
    @SerializedName("msg")
    val msg: String,
    @SerializedName("success")
    val success: String
) : Parcelable