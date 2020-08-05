package com.coffeetime.simplenetworkrequest.ui.landing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.coffeetime.simplenetworkrequest.R
import com.coffeetime.simplenetworkrequest.util.SharedPrefManager

class LandingActivity : AppCompatActivity() {

    private val viewModel: ViewModel by lazy {
        ViewModelProviders.of(this).get(LandingViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        Toast.makeText(
            this,
            ("User Logged in: " + SharedPrefManager.getInstance(this).isLoggedIn().toString()),
            Toast.LENGTH_SHORT
        ).show()


    }
}