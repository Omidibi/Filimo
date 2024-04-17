package com.omid.filimo.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserStatus(
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: String
) : Parcelable