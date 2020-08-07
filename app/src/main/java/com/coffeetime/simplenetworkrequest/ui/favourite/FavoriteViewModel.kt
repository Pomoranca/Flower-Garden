package com.coffeetime.simplenetworkrequest.ui.favourite

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.coffeetime.simplenetworkrequest.network.models.FavoriteFlower
import com.coffeetime.simplenetworkrequest.network.FlowerApi
import com.coffeetime.simplenetworkrequest.util.SharedPrefManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {
    val context = getApplication<Application>().applicationContext!!

    private val viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _favoriteFlowers = MutableLiveData<List<FavoriteFlower>>()

    val favoriteFlower: LiveData<List<FavoriteFlower>>
        get() = _favoriteFlowers

    private val token = SharedPrefManager.getInstance(context).getToken()

    init {
        listFavoriteFlowers()
    }

    private fun listFavoriteFlowers() {
        coroutineScope.launch {
            val getFavoriteFlowers = FlowerApi.retrofitService.getFavoriteFlowers(token)
            try {
                if (getFavoriteFlowers.favFlowers.isNotEmpty()) {
                    _favoriteFlowers.value = getFavoriteFlowers.favFlowers
                }
            } catch (e: Exception) {
                Log.i("ERROR", "List flowers error: " + e.localizedMessage!!)
            }
        }
    }
}