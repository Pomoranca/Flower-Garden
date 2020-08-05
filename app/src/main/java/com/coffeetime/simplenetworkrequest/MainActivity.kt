package com.coffeetime.simplenetworkrequest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.coffeetime.simplenetworkrequest.util.SharedPrefManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment? ?: return
        val navController = host.navController
        NavigationUI.setupWithNavController(bottomNavigation, navController)


        Toast.makeText(
            this,
            ("User Logged in: " + SharedPrefManager.getInstance(this).isLoggedIn().toString()),
            Toast.LENGTH_SHORT
        ).show()
    }


}