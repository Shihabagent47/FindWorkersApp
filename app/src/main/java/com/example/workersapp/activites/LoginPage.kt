package com.example.workersapp.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.workersapp.R

class LoginPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        val button1=findViewById<Button>(R.id.findWorkers)

        button1.setOnClickListener{
            val change_activity=Intent(this, DashBordActivity::class.java)
            startActivity(change_activity)
        }

        val button2=findViewById<Button>(R.id.loginWorkers)

        button2.setOnClickListener{
            val change_activity=Intent(this, LoginActivity::class.java)
            startActivity(change_activity)
        }
    }
}