package com.example.virtualeye.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.virtualeye.MainActivity
import com.example.virtualeye.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
        CoroutineScope(Dispatchers.IO).launch {
            delay(4000)
            val intent = Intent(this@SplashActivity, MainActivity::class.java).also {
                startActivity(it)
            }
        }


    }
}