package com.omid.filimo.fragments.loginFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.omid.filimo.api.WebServiceCaller
import com.omid.filimo.model.UserLoginModel
import com.omid.filimo.utils.configuration.AppConfiguration
import com.omid.filimo.utils.internetLiveData.CheckNetworkConnection
import com.omid.filimo.utils.networkAvailable.NetworkAvailable

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val webServiceCaller = WebServiceCaller()
    val checkNetworkConnection = CheckNetworkConnection(application)
    private val userLoginModel: LiveData<UserLoginModel> = webServiceCaller.userLoginModel

    fun getLogin(email: String, password: String): LiveData<UserLoginModel> {
        webServiceCaller.getUserLogin(email, password)
        return userLoginModel
    }

    fun checkNetworkAvailable(): Boolean {
        return NetworkAvailable.isNetworkAvailable(AppConfiguration.getContext())
    }
}