package com.coffeetime.simplenetworkrequest.ui.landing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.coffeetime.simplenetworkrequest.R

class LandingActivity : AppCompatActivity() {

    private val viewModel: ViewModel by lazy {
        ViewModelProviders.of(this).get(LandingViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)


    }
}