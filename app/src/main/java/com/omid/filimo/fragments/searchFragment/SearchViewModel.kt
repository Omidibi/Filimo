package com.omid.filimo.fragments.searchFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.omid.filimo.api.WebServiceCaller
import com.omid.filimo.model.AllVideoModel
import com.omid.filimo.model.SearchModel
import com.omid.filimo.utils.configuration.AppConfiguration
import com.omid.filimo.utils.internetLiveData.CheckNetworkConnection
import com.omid.filimo.utils.networkAvailable.NetworkAvailable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(application: Application) : AndroidViewModel(application) {

    val checkNetworkConnection = CheckNetworkConnection(application)
    private val webServiceCaller = WebServiceCaller()
    private val searchModel = MutableLiveData<SearchModel>()

    fun getSearchVideo(search: String): MutableLiveData<SearchModel> {
        if (checkNetworkConnection.value == true) {
            CoroutineScope(Dispatchers.IO).launch {
                webServiceCaller.getSearchVideo(search).apply {
                    searchModel.postValue(this)
                }
            }
        }
        return searchModel
    }

    fun networkAvailable(): Boolean {
        return NetworkAvailable.isNetworkAvailable(AppConfiguration.getContext())
    }
}