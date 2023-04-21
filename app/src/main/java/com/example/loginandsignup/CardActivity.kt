package com.example.loginandsignup

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.loginandsignup.databinding.ActivityCardBinding
import com.google.firebase.database.FirebaseDatabase


class CardActivity : AppCompatActivity() {


    private lateinit var binding : ActivityCardBinding
    private var db : FirebaseDatabase = FirebaseDatabase.getInstance()

    init{
        db.setPersistenceEnabled(true)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val f = intent.getStringExtra("FESTIVAL")
        val ref = db.getReferenceFromUrl("https://loginandsignup-1c003-default-rtdb.europe-west1.firebasedatabase.app/Festivals")
        ref.get().addOnSuccessListener { dataSnapshot ->
            Log.d("a", dataSnapshot.toString())
            val model = dataSnapshot.getValue(Festival::class.java)
            val festivalObj = model?.Names?.get(f) ?: return@addOnSuccessListener
            binding.festivalName.text = festivalObj.name;
            binding.festivalDate.text = festivalObj.datestart + "-" + festivalObj.dateend
            binding.festivalInfo.text = festivalObj.infotext
            binding.buttonLink.setOnClickListener{
                val uri = Uri.parse(festivalObj.webpage)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }
        }.addOnFailureListener { exception ->
            println(exception)
        }
    }

    private fun readData(name:String){

    }

}