package com.omid.filimo.ui.dashboard.category

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.omid.filimo.api.WebServiceCaller
import com.omid.filimo.model.CategoryModel
import com.omid.filimo.utils.configuration.AppConfiguration
import com.omid.filimo.utils.internetLiveData.CheckNetworkConnection
import com.omid.filimo.utils.networkAvailable.NetworkAvailable

class CategoryViewModel(application: Application) : AndroidViewModel(application) {

    private val webServiceCaller = WebServiceCaller()
    val checkNetworkConnection = CheckNetworkConnection(application)
    val categoryModel: LiveData<CategoryModel> = webServiceCaller.categoryModel

    init {
        checkNetworkConnection.observeForever { isConnect ->
            if (isConnect) {
                getCategories()
            }
        }
    }

    private fun getCategories() {
        if (checkNetworkConnection.value == true) {
            webServiceCaller.getCategories()
        }
    }

    fun networkAvailable(): Boolean {
        return NetworkAvailable.isNetworkAvailable(AppConfiguration.getContext())
    }
}