package com.coffeetime.simplenetworkrequest.ui.user

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.coffeetime.simplenetworkrequest.network.FlowerApi
import com.coffeetime.simplenetworkrequest.network.models.User
import com.coffeetime.simplenetworkrequest.util.SharedPrefManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext


    private val viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _user = MutableLiveData<User>()

    val user: LiveData<User>
        get() = _user

    init {
        getUserInfo()
    }

    private fun getUserInfo() {
        coroutineScope.launch {
            val token = SharedPrefManager.getInstance(context).getToken()
            val getUserInfoDeferred = FlowerApi.retrofitService.getInfo(token)
            try {
                _user.value = getUserInfoDeferred.user

            } catch (e: Exception) {
                showMessage(e.message.toString())
            }
        }
    }
    private fun showMessage(message: String){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}