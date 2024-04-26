package com.omid.filimo.fragments.forgetPassword

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.omid.filimo.api.WebServiceCaller
import com.omid.filimo.model.ForgetPasswordModel
import com.omid.filimo.utils.configuration.AppConfiguration
import com.omid.filimo.utils.internetLiveData.CheckNetworkConnection
import com.omid.filimo.utils.networkAvailable.NetworkAvailable

class ForgetPasswordViewModel(application: Application) : AndroidViewModel(application) {

    private val webServiceCaller = WebServiceCaller()
    val checkNetworkConnection = CheckNetworkConnection(application)
    private val forgetPasswordModel: LiveData<ForgetPasswordModel> = webServiceCaller.forgetPasswordModel

    fun getForgetPassword(email: String): LiveData<ForgetPasswordModel>{
        webServiceCaller.getForgetPassword(email)
        return forgetPasswordModel
    }

    fun checkNetworkAvailable(): Boolean {
        return NetworkAvailable.isNetworkAvailable(AppConfiguration.getContext())
    }
}