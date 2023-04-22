package com.example.loginandsignup.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.loginandsignup.R
import com.example.loginandsignup.adapters.AdapterPdfFavorite
import com.example.loginandsignup.databinding.ActivityFavoritesBinding
import com.example.loginandsignup.models.ModelPdf
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase


val database = Firebase.database
    val myRef = database.getReference("message")


class FavoritesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoritesBinding

    private lateinit var firebaseAuth: FirebaseAuth

    //arraylist to hold festivals
    private lateinit var festivalArrayList: ArrayList<ModelPdf>
    private lateinit var adapterPdfFavorite: AdapterPdfFavorite

    private lateinit var  dbRef : DatabaseReference

    companion object{
        const val TAG = "FAVORITE_DETAILS_TAG"
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        loadFavoriteFestivals()



        dbRef = FirebaseDatabase.getInstance().getReference("Festivals")

        myRef.setValue("Favorite List")

        myRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.getValue<String>()
                Log.d(TAG, "Value is: $value")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })

        //setup adapter
        adapterPdfFavorite = AdapterPdfFavorite(this@FavoritesActivity, festivalArrayList)
        //set adapter to recyclerview
        binding.favoriteRv.adapter = adapterPdfFavorite



    }

    private fun loadFavoriteFestivals(){
        //init arraylist
        festivalArrayList = ArrayList();

        val ref = FirebaseDatabase.getInstance().getReference("Favorites")
        ref.child(firebaseAuth.uid!!).child("Name")
            .addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    //clear arraylist, before starting adding data
                    festivalArrayList.clear()
                    for (ds in snapshot.children){
                      //get only name of festivals, rest of the info we have loaded in adapter class
                        val name = "${ds.child("name").value}"

                        //set to model
                        val modelPdf = ModelPdf()
                        modelPdf.name = name

                        //add model to list
                        festivalArrayList.add(modelPdf)
                    }


                }

                override fun onCancelled(error: DatabaseError) {

                }
            })

    }
}