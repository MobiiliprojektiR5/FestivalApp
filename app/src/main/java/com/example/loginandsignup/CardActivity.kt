package com.example.loginandsignup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.loginandsignup.databinding.ActivityCardBinding


class CardActivity : AppCompatActivity() {


    private lateinit var binding : ActivityCardBinding
    private lateinit var database : DataBaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.readdataBtn.setOnClickListener {
            val name : String = binding
        }
    }

    private fun readData(name:String){
        database = FirebaseDatabase.getInstance().getReference( path:"0")
    }

}