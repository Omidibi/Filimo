package com.omid.filimo.fragments.userProfileFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.omid.filimo.api.WebServiceCaller
import com.omid.filimo.model.ProfileUpdateModel
import com.omid.filimo.model.UserLoginModel
import com.omid.filimo.utils.configuration.AppConfiguration
import com.omid.filimo.utils.internetLiveData.CheckNetworkConnection
import com.omid.filimo.utils.networkAvailable.NetworkAvailable

class UserProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val webServiceCaller = WebServiceCaller()
    val checkNetworkConnection = CheckNetworkConnection(application)
    private val profileUpdateModel: LiveData<ProfileUpdateModel> = webServiceCaller.profileUpdateModel
    private val userLogin: LiveData<UserLoginModel> = webServiceCaller.userLogin

    fun getProfileUpdate(userId: String, name: String, email: String, password: String, phone: String): LiveData<ProfileUpdateModel> {
        webServiceCaller.getUserProfileUpdate(userId, name, email, password, phone)
        return profileUpdateModel
    }

    fun getUserLogin(email: String, password: String): LiveData<UserLoginModel> {
        webServiceCaller.getUserLogin(email, password)
        return userLogin
    }

    fun checkNetworkAvailable(): Boolean {
        return NetworkAvailable.isNetworkAvailable(AppConfiguration.getContext())
    }
}