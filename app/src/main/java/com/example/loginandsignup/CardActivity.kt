package com.example.loginandsignup

import FestivalModel
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.loginandsignup.databinding.ActivityCardBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class CardActivity : AppCompatActivity() {


    private lateinit var binding : ActivityCardBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = FirebaseDatabase.getInstance()
        db.setPersistenceEnabled(true)

        binding.festivalName.text = "name"
        binding.festivalInfo.text = "yolo"
        val ref = db.getReferenceFromUrl("https://loginandsignup-1c003-default-rtdb.europe-west1.firebasedatabase.app/")
        ref.get().addOnSuccessListener { dataSnapshot ->
            val models = mutableListOf<FestivalModel>()
            for (childSnapshot in dataSnapshot.children) {
                val model = childSnapshot.getValue(FestivalModel::class.java)
                if (model != null) {
                    models.add(model)
                }
            }
            binding.festivalInfo.text = models.toString()
        }.addOnFailureListener { exception ->
            println(exception)
        }


        /*
        val res = ref.get()
        if (res.result != null) {
            binding.festivalInfo.text = res.result.toString()
        }


        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot){
                binding.festivalName.text = dataSnapshot.getValue<String>()
                // Use the latitude value here
            }
            override fun onCancelled(error: DatabaseError) {
                // Handle errors here
            }
        })
        */

        binding.button.setOnClickListener {
            //val name : String = binding
        }
    }

    private fun readData(name:String){

    }

}