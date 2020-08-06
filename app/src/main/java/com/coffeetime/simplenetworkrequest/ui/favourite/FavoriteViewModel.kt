package com.coffeetime.simplenetworkrequest.ui.favourite

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.coffeetime.simplenetworkrequest.network.FavFlower
import com.coffeetime.simplenetworkrequest.network.FavFlowerX
import com.coffeetime.simplenetworkrequest.network.FlowerApi
import com.coffeetime.simplenetworkrequest.util.SharedPrefManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {
    val context = getApplication<Application>().applicationContext

    val viewModelJob = Job()

    val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _favoriteFlowers = MutableLiveData<List<FavFlowerX>>()

    val favoriteFlower: LiveData<List<FavFlowerX>>
        get() = _favoriteFlowers

    private val token = SharedPrefManager.getInstance(context).getToken()

    init {
        listFavoriteFlowers()
    }

    fun listFavoriteFlowers() {
        coroutineScope.launch {
            val getFavoriteFlowers = FlowerApi.retrofitService.getFavoriteFlowers(token)
            try {
                val listResult = getFavoriteFlowers.await()
                if (listResult.favFlowers.isNotEmpty()) {
                    _favoriteFlowers.value = getFavoriteFlowers.await().favFlowers
                }
            } catch (e: Exception) {
                Log.i("ERROR", "List flowers error: " + e.localizedMessage!!)
            }
        }
    }
}