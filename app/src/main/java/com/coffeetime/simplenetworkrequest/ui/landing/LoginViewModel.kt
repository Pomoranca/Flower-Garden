package com.coffeetime.simplenetworkrequest.ui.landing

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.coffeetime.simplenetworkrequest.network.FlowerApi
import com.coffeetime.simplenetworkrequest.network.models.NetworkResponse
import com.coffeetime.simplenetworkrequest.util.SharedPrefManager
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext


    private val _navigateToMainActivity = MutableLiveData<Boolean>()

    val navigateToMainActivity: LiveData<Boolean>
        get() = _navigateToMainActivity

    fun doneNavigating() {
        _navigateToMainActivity.value = null
    }


    fun loginUser(username: String, password: String) {
        val loginUserCall = FlowerApi.retrofitService.loginUserAsync(username, password)

        loginUserCall.enqueue(object : Callback<NetworkResponse> {
            override fun onFailure(call: Call<NetworkResponse>, t: Throwable) {
                call.cancel()
                showMessage("Network error")
            }

            override fun onResponse(
                call: Call<NetworkResponse>,
                response: Response<NetworkResponse>
            ) {
                if (response.isSuccessful) {
                    try {
                        SharedPrefManager.getInstance(context)
                            .saveToken(response.body()!!.authToken)

                        SharedPrefManager.getInstance(context).logIn()

                        _navigateToMainActivity.value = true

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                } else try {
                    val jObjError = JSONObject(response.errorBody()!!.string())
                    showMessage(jObjError.get("error").toString())
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

        })
    }

    fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}