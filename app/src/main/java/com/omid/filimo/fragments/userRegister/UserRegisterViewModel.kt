package com.omid.filimo.fragments.userRegister

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.omid.filimo.api.WebServiceCaller
import com.omid.filimo.model.UserRegisterModel
import com.omid.filimo.utils.configuration.AppConfiguration
import com.omid.filimo.utils.internetLiveData.CheckNetworkConnection
import com.omid.filimo.utils.networkAvailable.NetworkAvailable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserRegisterViewModel(application: Application) : AndroidViewModel(application) {

    private val webServiceCaller = WebServiceCaller()
    private val userRegisterModel = MutableLiveData<UserRegisterModel>()
    val checkNetworkConnection = CheckNetworkConnection(application)

    fun getRegister(name: String, email: String, password: String, phone: String): MutableLiveData<UserRegisterModel> {
        CoroutineScope(Dispatchers.IO).launch {
            webServiceCaller.getUserRegister(name, email, password, phone).apply {
                userRegisterModel.postValue(this)
            }
        }
        return userRegisterModel
    }

    fun checkNetworkAvailable(): Boolean {
        return NetworkAvailable.isNetworkAvailable(AppConfiguration.getContext())
    }
}