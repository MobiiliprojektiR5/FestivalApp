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


    // Haetaan festivaalin tiedot Firebase-tietokannasta festivaalin nimellä
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Yhdistetään activity_card.xml
        binding = ActivityCardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Haetaan FESTIVAL-parametri, jonka FestaritActivity on määrittänyt
        val f = intent.getStringExtra("FESTIVAL")
        // Tietokannan URL-osoite
        val ref = db.getReferenceFromUrl("https://loginandsignup-1c003-default-rtdb.europe-west1.firebasedatabase.app/Festivals")
        // Luetaan data get()-metodilla ja Listener odottaa että saa datan
        ref.get().addOnSuccessListener { dataSnapshot ->
            // Saatu JSON muutetaan festival-luokan instanssiksi
            val model = dataSnapshot.getValue(Festival::class.java)
            // Haetaan festivaali Names-nimisestä hakemistosta
            val festivalObj = model?.Names?.get(f) ?: return@addOnSuccessListener
            // Tallennetaan saadut tiedot TextVieweihin
            binding.festivalName.text = festivalObj.name;
            binding.festivalTown.text = festivalObj.locationtown
            binding.festivalDate.text = festivalObj.date
            binding.festivalInfo.text = festivalObj.infotext
            // Avataan festivaalin nettisivu kun painetaan buttonLink, osoite haetaan tietokannasta
            binding.buttonLink.setOnClickListener{
                val uri = Uri.parse(festivalObj.webpage)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }
            // Haetaan festivaalin index-luku kuvaa varten
            binding.festivalImage.setImageResource(getFestivalResource(intent.getIntExtra("INDEX", 0)))
        }.addOnFailureListener { exception ->
            println(exception)
        }

        // Takaisin-nappi
        binding.buttonBack.setOnClickListener{
            val intent = Intent(this, FestaritActivity::class.java)
            startActivity(intent)
        }
    }

    // Määritellään kuville indexit
    private fun getFestivalResource(index: Int): Int {
        if (index == 0) {
            return R.drawable.img
        } else if (index == 1) {
            return R.drawable.img_1
        }
        return R.drawable.img_2
    }
}