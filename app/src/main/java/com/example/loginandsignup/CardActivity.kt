package com.example.loginandsignup

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.loginandsignup.databinding.ActivityCardBinding
import com.google.firebase.database.FirebaseDatabase


class CardActivity : AppCompatActivity() {


    private lateinit var binding : ActivityCardBinding
    private var db : FirebaseDatabase = FirebaseDatabase.getInstance()
    private lateinit var buttonBack: AppCompatButton



    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val f = intent.getStringExtra("FESTIVAL")
        val ref = db.getReferenceFromUrl("https://loginandsignup-1c003-default-rtdb.europe-west1.firebasedatabase.app/Festivals")
        ref.get().addOnSuccessListener { dataSnapshot ->
            val model = dataSnapshot.getValue(Festival::class.java)
            val festivalObj = model?.Names?.get(f) ?: return@addOnSuccessListener
            binding.festivalName.text = festivalObj.name;
            binding.festivalDate.text = festivalObj.date
            binding.festivalInfo.text = festivalObj.infotext
            binding.buttonLink.setOnClickListener{
                val uri = Uri.parse(festivalObj.webpage)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }
            binding.festivalImage.setImageResource(getFestivalResource(intent.getIntExtra("INDEX", 0)))
        }.addOnFailureListener { exception ->
            println(exception)
        }

        binding.buttonBack.setOnClickListener{
            val intent = Intent(this, FestaritActivity::class.java)
            startActivity(intent)
        }
    }


    private fun getFestivalResource(index: Int): Int {
        if (index == 0) {
            return R.drawable.img
        } else if (index == 1) {
            return R.drawable.img_1
        }
        return R.drawable.img_2
    }
}