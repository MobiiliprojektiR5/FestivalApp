package com.example.loginandsignup.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.loginandsignup.R
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val logOutBtn = findViewById<Button>(R.id.LogOutBtn)
        logOutBtn.setOnClickListener {
           FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        val festitClick = findViewById<Button>(R.id.FestaritBtn)
        festitClick.setOnClickListener {
            val intent = Intent(this, FestaritActivity::class.java)
            startActivity(intent)
        }

        val favoritesClick = findViewById<Button>(R.id.suosikitBtn)
        favoritesClick.setOnClickListener {
            val intent = Intent(this, FavoritesActivity::class.java)
            startActivity(intent)
        }
    }

}