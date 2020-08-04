package com.coffeetime.simplenetworkrequest.util

import android.content.Context
import com.coffeetime.simplenetworkrequest.network.User

class SharedPrefManager(context: Context) {

    val sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()

    val isLoggedIn: Boolean = sharedPreferences.getBoolean("loggedIn", false)

    fun logIn(){
        editor.putBoolean("loggedIn", true)
    }

//    val user: User
//        get() {
//            return User(
//                sharedPreferences.getString("email", "")!!,
//                sharedPreferences.getString("password", "")!!,
//                sharedPreferences.getString("firstName", "")!!,
//                sharedPreferences.getString("lastName", "")!!,
//                sharedPreferences.getString("dateOfBirth", "")!!
//            )
//        }

    fun saveToken(token: String) {
        editor.putString("TOKEN_VALUE", token).apply()
    }

    fun getToken() : String {
        return sharedPreferences.getString("TOKEN_VALUE", "")!!
    }

    fun clear() {
        editor.clear()
        editor.apply()
    }

//    fun saveUser(
//        email: String,
//        password: String,
//        firstName: String,
//        lastName: String,
//        dateOfBirth: String
//    ) {
//
//        editor.putString("email", email)
//        editor.putString("password", password)
//        editor.putString("firstName", firstName)
//        editor.putString("lastName", lastName)
//        editor.putString("dateOfBirth", dateOfBirth)
//
//        editor.apply()
//
//    }


    companion object {
        private val SHARED_PREF_NAME = "saved"
        private var mInstance: SharedPrefManager? = null

        @Synchronized
        fun getInstance(mCtx: Context): SharedPrefManager {
            if (mInstance == null) {
                mInstance = SharedPrefManager(mCtx)
            }
            return mInstance as SharedPrefManager
        }
    }

}