package com.omid.filimo.model

import com.google.gson.annotations.SerializedName

data class UserRegisterModel(

    @SerializedName("ALL_IN_ONE_VIDEO")
    val userRegister: List<UserRegister>
)