package com.coffeetime.simplenetworkrequest

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.coffeetime.simplenetworkrequest.ui.landing.LandingActivity
import com.coffeetime.simplenetworkrequest.util.SharedPrefManager
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashActivity : Activity() {
    /** Duration of wait  */
    private val SPLASH_DISPLAY_LENGTH = 1000

    /** Called when the activity is first created.  */
    public override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)
        setContentView(R.layout.activity_splash)

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/Handler()
//            .postDelayed({ /* Create an Intent that will start the Menu-Activity. */
//                val mainIntent = Intent(this@SplashActivity, MainActivity::class.java)
//                this@SplashActivity.startActivity(mainIntent)
//                finish()
//            }, SPLASH_DISPLAY_LENGTH.toLong())

        CoroutineScope(Dispatchers.Main).launch {
            startAnimation()
            startActivity()
        }


    }

    private suspend fun startAnimation() {
        motion_layout.transitionToEnd()
        delay(1000)

    }


    fun startActivity() {
        if (SharedPrefManager.getInstance(this).isLoggedIn) {
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            startActivity(Intent(this, LandingActivity::class.java))

        }
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

}