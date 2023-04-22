package com.example.loginandsignup.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.loginandsignup.R
import com.example.loginandsignup.databinding.ActivityCardBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class CardActivity : AppCompatActivity() {


    private lateinit var binding : ActivityCardBinding
    private var db : FirebaseDatabase = FirebaseDatabase.getInstance()
    private lateinit var buttonBack: AppCompatButton
    private lateinit var firebaseAuth: FirebaseAuth



    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()
        binding = ActivityCardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val f = intent.getStringExtra("FESTIVAL")
        val ref = db.getReferenceFromUrl("https://loginandsignup-1c003-default-rtdb.europe-west1.firebasedatabase.app/Festivals")
        ref.get().addOnSuccessListener { dataSnapshot ->
            val model = dataSnapshot.getValue(Festival::class.java)
            val festivalObj = model?.Names?.get(f) ?: return@addOnSuccessListener
            binding.festivalName.text = festivalObj.name
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

        //handle click, add/remove favorite
        binding.addFavBtn.setOnClickListener {
            if(firebaseAuth.currentUser == null) {
                 //user not logged in, cant do favorite functionality
                 Toast.makeText(this, "You are not logged in", Toast.LENGTH_SHORT).show()
             }
             else{
                 //user is logged in, we can do favorite functionality
                 addToFavorite()
             }
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

    fun addToFavorite() {

        //get from firebase
        var dateend = ""
        var datestart = ""
        var imageurl = ""
        var infotext = ""
        var locationaddress = ""
        var locationtown = ""
        var name = ""
        var tickets = ""
        var webpage = ""

        //setup data to add in db
        val hashMap = HashMap<String, Any>()
        hashMap["dateend"] = dateend
        hashMap["datestart"] = datestart
        hashMap["imageurl"] = imageurl
        hashMap["infotext"] = infotext
        hashMap["locationaddress"] = locationaddress
        hashMap["locationtown"] = locationtown
        hashMap["name"] = name
        hashMap["tickets"] = tickets
        hashMap["webpage"] = webpage
        val TAG = "ADDED_FAV_TAG"

        Log.d(TAG, "addToFavorite: Adding to fav")


        //save to db
        val ref = FirebaseDatabase.getInstance().getReference("Favorites")
        ref.child(firebaseAuth.uid!!).child("Name").child("")
            .setValue(hashMap)
            .addOnSuccessListener {
                //added to fav
                Log.d(TAG, "addToFavorite: Added to fav")
                Toast.makeText(this, "Added to favorites", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                //failed to add to fav
                Log.d(TAG, "addToFavorite: Failed to add to fav due to ${e.message}")
                Toast.makeText(
                    this,
                    "Failed to add to fav due to ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }
}