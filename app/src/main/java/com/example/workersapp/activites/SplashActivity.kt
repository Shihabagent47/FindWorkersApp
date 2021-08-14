package com.example.workersapp.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.workersapp.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(
            {
                // Launch the Main Activity
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish() // Call this when your activity is done and should be closed.
            },
            2000
        )



    }

}