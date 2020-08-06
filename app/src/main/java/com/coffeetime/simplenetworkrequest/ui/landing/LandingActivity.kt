package com.coffeetime.simplenetworkrequest.ui.landing

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.coffeetime.simplenetworkrequest.R

class LandingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

//        Toast.makeText(
//            this,
//            ("User Logged in: " + SharedPrefManager.getInstance(this).isLoggedIn().toString()),
//            Toast.LENGTH_SHORT
//        ).show()


    }
}