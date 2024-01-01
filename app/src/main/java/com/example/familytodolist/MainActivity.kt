package com.example.familytodolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun goToLogin(view: View) {
        var intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}