package com.omid.filimo.api

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.omid.filimo.model.AllVideoModel
import com.omid.filimo.model.BannerModel
import com.omid.filimo.model.CatListByIdModel
import com.omid.filimo.model.CategoryModel
import com.omid.filimo.model.ForgetPasswordModel
import com.omid.filimo.model.HomeVideos
import com.omid.filimo.model.LatestVideoModel
import com.omid.filimo.model.ProfileUpdateModel
import com.omid.filimo.model.SearchModel
import com.omid.filimo.model.SingleVideoModel
import com.omid.filimo.model.UserLoginModel
import com.omid.filimo.model.UserProfileModel
import com.omid.filimo.model.UserRegisterModel
import com.omid.filimo.model.UserStatusModel
import com.omid.filimo.utils.configuration.AppConfiguration
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.net.SocketException
import java.net.SocketTimeoutException

class WebServiceCaller {

    val latestVideoModel = MutableLiveData<LatestVideoModel>()
    val categoryModel = MutableLiveData<CategoryModel>()
    val catListByIdModel = MutableLiveData<CatListByIdModel>()
    val singleVideoModel = MutableLiveData<SingleVideoModel>()
    val userLoginModel = MutableLiveData<UserLoginModel>()
    val profileUpdateModel = MutableLiveData<ProfileUpdateModel>()
    val forgetPasswordModel = MutableLiveData<ForgetPasswordModel>()

    suspend fun getBanner(): BannerModel? {
        return try {
            return if (InitIService.iService.banner().isSuccessful) {
                InitIService.iService.banner().body()
            } else {
                null
            }
        } catch (e: Exception) {
            when (e) {
                is SocketTimeoutException, is SocketException -> {
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(AppConfiguration.getContext(), e.message, Toast.LENGTH_LONG).show()
                    }
                }

                else -> throw e
            }
            null
        }
    }

    suspend fun getHomeVideo(): HomeVideos? {
        return try {
            return if (InitIService.iService.homeVideo().isSuccessful) {
                InitIService.iService.homeVideo().body()
            } else {
                null
            }
        } catch (e: Exception) {
            when (e) {
                is SocketTimeoutException, is SocketException -> {
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(AppConfiguration.getContext(), e.message, Toast.LENGTH_LONG).show()
                    }
                }

                else -> throw e
            }
            null
        }
    }

    suspend fun getAllVideo(): AllVideoModel? {
        return try {
            return if (InitIService.iService.allVideo().isSuccessful) {
                InitIService.iService.allVideo().body()
            } else {
                null
            }
        } catch (e: Exception) {
            when (e) {
                is SocketTimeoutException, is SocketException -> {
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(AppConfiguration.getContext(), e.message, Toast.LENGTH_LONG).show()
                    }
                }

                else -> throw e
            }
            null
        }
    }

    fun getLatestVideo() {
        try {
            CompositeDisposable().apply {
                val disposable = InitIService.iService.latestVideo()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ latestVideoModel ->
                        this@WebServiceCaller.latestVideoModel.postValue(latestVideoModel)
                    }, { error ->
                        Log.e("", error.message.toString())
                    })
                this.add(disposable)
            }
        } catch (e: Exception) {
            when (e) {
                is SocketTimeoutException, is SocketException -> {
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(AppConfiguration.getContext(), e.message, Toast.LENGTH_LONG).show()
                    }
                }

                else -> throw e
            }
        }
    }

    fun getCategories() {
        try {
            CompositeDisposable().apply {
                val disposable = InitIService.iService.categories()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ categoryModel ->
                        this@WebServiceCaller.categoryModel.postValue(categoryModel)
                    }, { error ->
                        Log.e("", error.message.toString())
                    })
                this.add(disposable)
            }
        } catch (e: Exception) {
            when (e) {
                is SocketTimeoutException, is SocketException -> {
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(AppConfiguration.getContext(), e.message, Toast.LENGTH_LONG).show()
                    }
                }

                else -> throw e
            }
        }
    }

    fun getVideoListByCatId(catId: String) {
        try {
            CompositeDisposable().apply {
                val disposable = InitIService.iService.videoListByCatId(catId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ catListByIdModel ->
                        this@WebServiceCaller.catListByIdModel.postValue(catListByIdModel)
                    }, { error ->
                        Log.e("", error.message.toString())
                    })
                this.add(disposable)
            }
        } catch (e: Exception) {
            when (e) {
                is SocketTimeoutException, is SocketException -> {
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(AppConfiguration.getContext(), e.message, Toast.LENGTH_LONG).show()
                    }
                }

                else -> throw e
            }
        }
    }

    fun getSingleVideo(videoId: String) {
        try {
            CompositeDisposable().apply {
                val disposable = InitIService.iService.singleVideo(videoId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ singleVideoModel ->
                        this@WebServiceCaller.singleVideoModel.postValue(singleVideoModel)
                    }, { error ->
                        Log.e("", error.message.toString())
                    })
                this.add(disposable)
            }
        } catch (e: Exception) {
            when (e) {
                is SocketTimeoutException, is SocketException -> {
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(AppConfiguration.getContext(), e.message, Toast.LENGTH_LONG).show()
                    }
                }

                else -> throw e
            }
        }
    }

    suspend fun getSearchVideo(search: String): SearchModel? {
        return try {
            return if (InitIService.iService.searchVideo(search).isSuccessful) {
                InitIService.iService.searchVideo(search).body()
            } else {
                null
            }
        } catch (e: Exception) {
            when (e) {
                is SocketTimeoutException, is SocketException -> {
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(AppConfiguration.getContext(), e.message, Toast.LENGTH_LONG).show()
                    }
                }

                else -> throw e
            }
            null
        }
    }

    suspend fun getUserRegister(name: String, email: String, password: String, phone: String): UserRegisterModel? {
        return try {
            return if (InitIService.iService.userRegister(name, email, password, phone).isSuccessful) {
                InitIService.iService.userRegister(name, email, password, phone).body()
            } else {
                null
            }
        } catch (e: Exception) {
            when (e) {
                is SocketTimeoutException, is SocketException -> {
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(AppConfiguration.getContext(), e.message, Toast.LENGTH_LONG).show()
                    }
                }

                else -> throw e
            }
            null
        }
    }

    fun getUserLogin(email: String, password: String) {
        try {
            CompositeDisposable().apply {
                val disposable = InitIService.iService.userLogin(email, password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ userLoginModel ->
                        this@WebServiceCaller.userLoginModel.postValue(userLoginModel)
                    }, { error ->
                        Log.e("", error.message.toString())
                    })
                this.add(disposable)
            }
        } catch (e: Exception) {
            when (e) {
                is SocketTimeoutException, is SocketException -> {
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(AppConfiguration.getContext(), e.message, Toast.LENGTH_LONG).show()
                    }
                }

                else -> throw e
            }
        }
    }

    suspend fun getUserProfile(id: String): UserProfileModel? {
        return try {
            return if (InitIService.iService.userProfile(id).isSuccessful) {
                InitIService.iService.userProfile(id).body()
            } else {
                null
            }
        } catch (e: Exception) {
            when (e) {
                is SocketTimeoutException, is SocketException -> {
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(AppConfiguration.getContext(), e.message, Toast.LENGTH_LONG).show()
                    }
                }

                else -> throw e
            }
            null
        }
    }

    fun getUserProfileUpdate(userId: String, name: String, email: String, password: String, phone: String) {
        try {
            CompositeDisposable().apply {
                val disposable = InitIService.iService.userProfileUpdate(userId, name, email, password, phone)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ profileUpdateModel ->
                            this@WebServiceCaller.profileUpdateModel.postValue(profileUpdateModel)
                        }, { error ->
                            Log.e("", error.message.toString())
                        })
                this.add(disposable)
            }
        } catch (e: Exception) {
            when (e) {
                is SocketTimeoutException, is SocketException -> {
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(AppConfiguration.getContext(), e.message, Toast.LENGTH_LONG).show()
                    }
                }

                else -> throw e
            }
        }
    }

    suspend fun getUserStatus(userId: String): UserStatusModel? {
        return try {
            return if (InitIService.iService.userStatus(userId).isSuccessful) {
                InitIService.iService.userStatus(userId).body()
            } else {
                null
            }
        } catch (e: Exception) {
            when (e) {
                is SocketTimeoutException, is SocketException -> {
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(AppConfiguration.getContext(), e.message, Toast.LENGTH_LONG).show()
                    }
                }

                else -> throw e
            }
            null
        }
    }

    fun getForgetPassword(email: String) {
        try {
            CompositeDisposable().apply {
                val disposable = InitIService.iService.forgetPassword(email)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ forgetPasswordModel ->
                        this@WebServiceCaller.forgetPasswordModel.postValue(forgetPasswordModel)
                    }, { error ->
                        Log.e("", error.message.toString())
                    })
                this.add(disposable)
            }
        } catch (e: Exception) {
            when (e) {
                is SocketTimeoutException, is SocketException -> {
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(AppConfiguration.getContext(), e.message, Toast.LENGTH_LONG).show()
                    }
                }

                else -> throw e
            }
        }
    }
}