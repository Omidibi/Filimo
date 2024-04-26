package com.omid.filimo.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserLogin(
    @SerializedName("email")
    val email: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("success")
    val success: String,
    @SerializedName("user_id")
    val userId: String
) : Parcelable