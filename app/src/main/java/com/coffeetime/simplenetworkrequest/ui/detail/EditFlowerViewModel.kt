package com.coffeetime.simplenetworkrequest.ui.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.coffeetime.simplenetworkrequest.network.Flower
import com.coffeetime.simplenetworkrequest.network.FlowerApi
import com.coffeetime.simplenetworkrequest.util.SharedPrefManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.await
import java.lang.Exception

class EditFlowerViewModel(flower: Flower, app: Application) : AndroidViewModel(app) {
    private val context = getApplication<Application>().applicationContext


    private val _selectedFlower = MutableLiveData<Flower>()

    val selectedFlower: LiveData<Flower>
        get() = _selectedFlower

    val _markedAsFavorite = MutableLiveData<Boolean>()

    val viewModelJob = Job()

    val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    /** Mark flower as favorite */
    fun markFavoriteFlower() {
        coroutineScope.launch {
            val markFavoriteFlower = FlowerApi.retrofitService.markFavoriteFlower(selectedFlower.value!!.id, SharedPrefManager.getInstance(context).getToken())

            try {
                markFavoriteFlower.await()
                Log.i("MARKED", "MARKED AS FAVORITE")

            } catch (e: Exception) {

            }
        }
    }


    init {
        _selectedFlower.value = flower
    }


}