package com.omid.filimo.model

import com.google.gson.annotations.SerializedName

data class UserLoginModel(

    @SerializedName("ALL_IN_ONE_VIDEO")
    val userLogin: List<UserLogin>
)