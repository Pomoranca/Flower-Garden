package com.coffeetime.simplenetworkrequest.ui.overview

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.coffeetime.simplenetworkrequest.network.Flower
import com.coffeetime.simplenetworkrequest.network.FlowerApi
import com.coffeetime.simplenetworkrequest.network.UserX
import com.coffeetime.simplenetworkrequest.util.SharedPrefManager
import com.coffeetime.simplenetworkrequest.util.SharedPrefManager.Companion.CURRENT_PAGE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class FlowerApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext


    private val _status = MutableLiveData<FlowerApiStatus>()

    val status: LiveData<FlowerApiStatus>
        get() = _status

    private val _flowers = MutableLiveData<List<Flower>>()

    val flowers: LiveData<List<Flower>>
        get() = _flowers

    private val _userInfo = MutableLiveData<UserX>()

    val userInfo: LiveData<UserX>
        get() = _userInfo

    private val _navigateToSelectedFlower = MutableLiveData<Flower>()

    val navigateToSelectedProperty: LiveData<Flower>
        get() = _navigateToSelectedFlower

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main
    )

    /** Extension function to add flowers to MutableLiveData */

    private fun <T> MutableLiveData<List<T>>.add(item: T) {

        val updatedItems = this.value as ArrayList

        updatedItems.add(item)
        this.value = updatedItems

    }

    init {
        getFlowers()
        getUserInfo()
    }

    /** Shows username on start */

    private fun getUserInfo() {
        coroutineScope.launch {
            val token = SharedPrefManager.getInstance(context).getToken()
            val getUserInfoDeferred = FlowerApi.retrofitService.getInfo(token)
            try {
                val userInfo = getUserInfoDeferred.await()
                _userInfo.value = userInfo.user

            } catch (e: Exception) {
                showMessage(e.message.toString())
            }
        }
    }

    /** List all flowers */

    private fun getFlowers() {
        coroutineScope.launch {
            val getFlowersDeferred = FlowerApi.retrofitService.getAllFlowersAsync(CURRENT_PAGE)

            try {
                _status.value =
                    FlowerApiStatus.LOADING
                val listResult = getFlowersDeferred.await()
                _status.value =
                    FlowerApiStatus.DONE

                if (listResult.flowers.isNotEmpty()) {

                    _flowers.value = listResult.flowers

                }
            } catch (e: Exception) {
                _status.value =
                    FlowerApiStatus.ERROR

                _flowers.value = ArrayList()

            }
        }
    }

    /** When flowers from page 1 are loaded, load more flowers */

    fun addFlowers() {
        coroutineScope.launch {
            val getFlowersDeferred = FlowerApi.retrofitService.getAllFlowersAsync(CURRENT_PAGE)

            try {
                _status.value =
                    FlowerApiStatus.LOADING
                val listResult = getFlowersDeferred.await()

                if (listResult.flowers.isNotEmpty()) {
                    for (element in listResult.flowers) {
                        _flowers.add(element)
                    }
                    _status.value = FlowerApiStatus.DONE

                } else {
                    _status.value = FlowerApiStatus.DONE
                }

            } catch (e: Exception) {
                _status.value =
                    FlowerApiStatus.ERROR
                _flowers.value = ArrayList()

            }
        }

    }




    fun displayFlowerDetails(flower: Flower) {
        _navigateToSelectedFlower.value = flower
    }

    fun displayFlowerDetailsComplete() {
        _navigateToSelectedFlower.value = null
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
        CURRENT_PAGE = 1

    }

    fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }


}