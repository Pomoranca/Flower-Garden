package com.coffeetime.simplenetworkrequest.ui.landing

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.coffeetime.simplenetworkrequest.network.FlowerApi
import com.coffeetime.simplenetworkrequest.network.models.NetworkResponse
import kotlinx.coroutines.Job
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


enum class UserApiStatus {}

class RegisterViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext


    private val _token = MutableLiveData<String>()

    private val _status = MutableLiveData<UserApiStatus>()


    private val _navigateToLandingFragment = MutableLiveData<Boolean>()

    val navigateToLandingFragment : LiveData<Boolean>
    get() = _navigateToLandingFragment

    fun doneNavigating(){
        _navigateToLandingFragment.value = null
    }



    private var viewModelJob = Job()

    fun registerUser(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
        dob: String
    ) {
        val callUser =
            FlowerApi.retrofitService.registerUserAsync(email, password, firstName, lastName, dob)

        callUser.enqueue(object : Callback<NetworkResponse> {

            override fun onFailure(call: Call<NetworkResponse>, t: Throwable) {
                call.cancel()
               showMessage("Network error")
            }
            override fun onResponse(
                call: Call<NetworkResponse>,
                response: Response<NetworkResponse>
            ) {

                if (response.isSuccessful) {
//                    SharedPrefManager.getInstance(context).saveUser(email, password, firstName, lastName, dob)

                    try {
                        _navigateToLandingFragment.value = true
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

    fun showMessage(message: String){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}