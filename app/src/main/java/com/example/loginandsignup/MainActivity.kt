package com.example.loginandsignup

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*val options = FirebaseOptions.Builder()
            .setApplicationId("your-app-id")
            .setApiKey("your-api-key")
            .setDatabaseUrl("your-database-url")
            .build()

        FirebaseApp.initializeApp(this)
        */
        setContentView(R.layout.activity_main)


        val logOutBtn = findViewById<Button>(R.id.LogOutBtn)
        logOutBtn.setOnClickListener {
           FirebaseAuth.getInstance().signOut()
            val intent = Intent(this,SignInActivity::class.java)
            startActivity(intent)
        }

        val festitClick =findViewById<Button>(R.id.FestaritBtn)
        festitClick.setOnClickListener {
            val intent = Intent(this, FestaritActivity::class.java)
            startActivity(intent)
        }
    }
}