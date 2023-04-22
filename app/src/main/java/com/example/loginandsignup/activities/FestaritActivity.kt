package com.example.loginandsignup.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.loginandsignup.R
import com.example.loginandsignup.databinding.ActivityFestaritBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.*


class FestaritActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFestaritBinding


    private lateinit var countdownTextRock: TextView
    //private lateinit var countdownTextHanko: TextView
    private lateinit var countdownTextNummi: TextView
    private lateinit var countdownTextHimos: TextView
    //private lateinit var countdownTextIskelma: TextView
    private lateinit var countdownTextProvinssi: TextView
    private lateinit var countdownTextTuska: TextView
    private lateinit var countdownTextRuis: TextView
    //private lateinit var countdownTextPop: TextView
    private lateinit var countdownTextQstock: TextView
    private lateinit var countdownTextKotka: TextView
    private lateinit var countdownTextFlow: TextView
    private lateinit var countdownTextBlock: TextView
    private lateinit var countdownTextFolk: TextView
    private lateinit var countdownTextDown: TextView

    private val handler = Handler(Looper.getMainLooper())

    private lateinit var firebaseAuth: FirebaseAuth

    companion object {
        fun removeFromFavorite(context: Context){
            val TAG = "REMOVE_FAV_TAG"
            Log.d(TAG, "removeFromFavorite: Removing from fav")

            val firebaseAuth = FirebaseAuth.getInstance()

            //database ref
            val ref = FirebaseDatabase.getInstance().getReference("Favorites")
            ref.child(firebaseAuth.uid!!).child("Name").child("")
                .removeValue()
                .addOnSuccessListener {
                    Log.d(TAG, "remoFromFavorite: Removed from fav")
                    Toast.makeText(context, "Removed from favorites", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e->
                    Log.d(TAG, "removeFromFavorite: Failed to remove from fav due to ${e.message}")
                    Toast.makeText(context, "Failed to remove from fav due to ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }

        //TAG
        const val TAG = "FAVORITE_DETAILS_TAG"
    }

    //will hold a boolean value false/true to indicate either is in favorite list or not
    private var isInMyFavorite = false


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_festarit)
        binding = ActivityFestaritBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //init firebase auth
        firebaseAuth = FirebaseAuth.getInstance()
        if (firebaseAuth.currentUser != null) {
            //user is logged in, check if festival is in fav or not
            checkIsFavorite()
        }

         countdownTextRock = findViewById(R.id.CdRock_text)
       // countdownTextHanko = findViewById(R.id.CdHanko_Text)
        countdownTextNummi = findViewById(R.id.CdNummi_text)
        countdownTextHimos = findViewById(R.id.CdHimos_text)
       // countdownTextIskelma = findViewById(R.id.CdIskelma_text)
        countdownTextProvinssi = findViewById(R.id.CdProvinssi_text)
        countdownTextTuska = findViewById(R.id.CdTuska_text)
        countdownTextRuis = findViewById(R.id.CdRuis_text)
       // countdownTextPop = findViewById(R.id.Cdpop_text)
        countdownTextQstock = findViewById(R.id.CdQstock_text)
        countdownTextKotka = findViewById(R.id.CdKotka_text)
        countdownTextFlow = findViewById(R.id.CdFlow_text)
        countdownTextBlock = findViewById(R.id.CdBlock_text)
        countdownTextFolk = findViewById(R.id.CdFolk_text)
        countdownTextDown = findViewById(R.id.CdDown_text)

        // Update TextView every second
         handler.post(object : Runnable{
            override fun run() {
                // Keep the postDelayed before the updateTime(), so when the event ends, the handler will stop too.
                handler.postDelayed(this, 1000)
                updateTime()
            }
        })

        val openCardClick = findViewById<Button>(R.id.RockfestBtn)
        openCardClick.setOnClickListener {
            val intent = Intent(this, CardActivity::class.java)
            intent.putExtra("FESTIVAL", "Rockfest")
            intent.putExtra("INDEX", 0)
            this.startActivity(intent)
        }

        val nummiClick = findViewById<Button>(R.id.NummirockBtn)
        nummiClick.setOnClickListener {
            val intent = Intent(this, CardActivity::class.java)
            intent.putExtra("FESTIVAL", "Nummirock")
            intent.putExtra("INDEX", 1)
            this.startActivity(intent)
        }

        val himoClick = findViewById<Button>(R.id.HimosBtn)
        himoClick.setOnClickListener {
            val intent = Intent(this, CardActivity::class.java)
            intent.putExtra("FESTIVAL", "Himos")
            intent.putExtra("INDEX", 2)
            this.startActivity(intent)
        }

        val battreClick = findViewById<Button>(R.id.FolkBtn)
        battreClick.setOnClickListener {
            val intent = Intent(this, CardActivity::class.java)
            intent.putExtra("FESTIVAL", "Bättre Folk")
            intent.putExtra("INDEX", 1)
            this.startActivity(intent)
        }

        val provinssiClick = findViewById<Button>(R.id.ProvinssiBtn)
        provinssiClick.setOnClickListener {
            val intent = Intent(this, CardActivity::class.java)
            intent.putExtra("FESTIVAL", "Provinssi")
            intent.putExtra("INDEX", 1)
            this.startActivity(intent)
        }

        val tuskaClick = findViewById<Button>(R.id.TuskaBtn)
        tuskaClick.setOnClickListener {
            val intent = Intent(this, CardActivity::class.java)
            intent.putExtra("FESTIVAL", "Tuska")
            intent.putExtra("INDEX", 0)
            this.startActivity(intent)
        }

        val ruisClick = findViewById<Button>(R.id.RuisBtn)
        ruisClick.setOnClickListener {
            val intent = Intent(this, CardActivity::class.java)
            intent.putExtra("FESTIVAL", "Ruisrock")
            intent.putExtra("INDEX", 2)
            this.startActivity(intent)
        }

        val qstockClick = findViewById<Button>(R.id.QstockBtn)
        qstockClick.setOnClickListener {
            val intent = Intent(this, CardActivity::class.java)
            intent.putExtra("FESTIVAL", "Qstock")
            intent.putExtra("INDEX", 1)
            this.startActivity(intent)
        }

        val kotkaClick = findViewById<Button>(R.id.KotkanBtn)
        kotkaClick.setOnClickListener {
            val intent = Intent(this, CardActivity::class.java)
            intent.putExtra("FESTIVAL", "Kotkan meripäivät")
            intent.putExtra("INDEX", 2)
            this.startActivity(intent)
        }

        val blockClick = findViewById<Button>(R.id.BlockBtn)
        blockClick.setOnClickListener {
            val intent = Intent(this, CardActivity::class.java)
            intent.putExtra("FESTIVAL", "Blockfest")
            intent.putExtra("INDEX", 0)
            this.startActivity(intent)
        }

        val laituriClick = findViewById<Button>(R.id.DownBtn)
        laituriClick.setOnClickListener {
            val intent = Intent(this, CardActivity::class.java)
            intent.putExtra("FESTIVAL", "Down By The Laituri")
            intent.putExtra("INDEX", 2)
            this.startActivity(intent)
        }
        val flowClick = findViewById<Button>(R.id.FlowBtn)
        flowClick.setOnClickListener {
            val intent = Intent(this, CardActivity::class.java)
            intent.putExtra("FESTIVAL", "Flow")
            intent.putExtra("INDEX", 0)
            this.startActivity(intent)
        }
    }




    private fun checkIsFavorite() {
        Log.d(TAG, "checkIsFavorite: Checking if festival is in fav or not")

        val ref = FirebaseDatabase.getInstance().getReference("Favorites")
        ref.child(firebaseAuth.uid!!).child("Name").child("")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    isInMyFavorite = snapshot.exists()
                    if (isInMyFavorite) {
                        //available in favorites
                        Log.d(TAG, "onDataChange: available in favorites")
                    } else {
                        //not available in favorites
                        Log.d(TAG, "onDataChange: not available in favorites")
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
    }

    @SuppressLint("SetTextI18n")
    fun updateTime() {
        //Set current date
        val currentDate = Calendar.getInstance()
        //Set event dates
        val eventDateRock = Calendar.getInstance()
        eventDateRock[Calendar.YEAR] = 2023
        eventDateRock[Calendar.MONTH] = 5
        eventDateRock[Calendar.DAY_OF_MONTH] = 8
        eventDateRock[Calendar.HOUR] = 0
        eventDateRock[Calendar.MINUTE] = 0
        eventDateRock[Calendar.SECOND] = 0
        eventDateRock.timeZone = TimeZone.getTimeZone("EEST")

        val eventDateHanko = Calendar.getInstance()
        eventDateHanko[Calendar.YEAR] = 2023
        eventDateHanko[Calendar.MONTH] = 5
        eventDateHanko[Calendar.DAY_OF_MONTH] = 18
        eventDateHanko[Calendar.HOUR] = 0
        eventDateHanko[Calendar.MINUTE] = 0
        eventDateHanko[Calendar.SECOND] = 0
        eventDateHanko.timeZone = TimeZone.getTimeZone("EEST")

        val eventDateNummi = Calendar.getInstance()
        eventDateNummi[Calendar.YEAR] = 2023
        eventDateNummi[Calendar.MONTH] = 5
        eventDateNummi[Calendar.DAY_OF_MONTH] = 21
        eventDateNummi[Calendar.HOUR] = 0
        eventDateNummi[Calendar.MINUTE] = 0
        eventDateNummi[Calendar.SECOND] = 0
        eventDateNummi.timeZone = TimeZone.getTimeZone("EEST")

        val eventDateHimos = Calendar.getInstance()
        eventDateHimos[Calendar.YEAR] = 2023
        eventDateHimos[Calendar.MONTH] = 5
        eventDateHimos[Calendar.DAY_OF_MONTH] = 22
        eventDateHimos[Calendar.HOUR] = 0
        eventDateHimos[Calendar.MINUTE] = 0
        eventDateHimos[Calendar.SECOND] = 0
        eventDateHimos.timeZone = TimeZone.getTimeZone("EEST")

        val eventDateIskelma = Calendar.getInstance()
        eventDateIskelma[Calendar.YEAR] = 2023
        eventDateIskelma[Calendar.MONTH] = 5
        eventDateIskelma[Calendar.DAY_OF_MONTH] = 29
        eventDateIskelma[Calendar.HOUR] = 0
        eventDateIskelma[Calendar.MINUTE] = 0
        eventDateIskelma[Calendar.SECOND] = 0
        eventDateIskelma.timeZone = TimeZone.getTimeZone("EEST")

        val eventDateProvinssi = Calendar.getInstance()
        eventDateProvinssi[Calendar.YEAR] = 2023
        eventDateProvinssi[Calendar.MONTH] = 5
        eventDateProvinssi[Calendar.DAY_OF_MONTH] = 29
        eventDateProvinssi[Calendar.HOUR] = 0
        eventDateProvinssi[Calendar.MINUTE] = 0
        eventDateProvinssi[Calendar.SECOND] = 0
        eventDateProvinssi.timeZone = TimeZone.getTimeZone("EEST")

        val eventDateTuska = Calendar.getInstance()
        eventDateTuska[Calendar.YEAR] = 2023
        eventDateTuska[Calendar.MONTH] = 5
        eventDateTuska[Calendar.DAY_OF_MONTH] = 30
        eventDateTuska[Calendar.HOUR] = 0
        eventDateTuska[Calendar.MINUTE] = 0
        eventDateTuska[Calendar.SECOND] = 0
        eventDateTuska.timeZone = TimeZone.getTimeZone("EEST")

        val eventDateRuis  = Calendar.getInstance()
        eventDateRuis[Calendar.YEAR] = 2023
        eventDateRuis[Calendar.MONTH] = 6
        eventDateRuis[Calendar.DAY_OF_MONTH] = 7
        eventDateRuis[Calendar.HOUR] = 0
        eventDateRuis[Calendar.MINUTE] = 0
        eventDateRuis[Calendar.SECOND] = 0
        eventDateRuis.timeZone = TimeZone.getTimeZone("EEST")

        val eventDatePop = Calendar.getInstance()
        eventDatePop[Calendar.YEAR] = 2023
        eventDatePop[Calendar.MONTH] = 6
        eventDatePop[Calendar.DAY_OF_MONTH] = 13
        eventDatePop[Calendar.HOUR] = 0
        eventDatePop[Calendar.MINUTE] = 0
        eventDatePop[Calendar.SECOND] = 0
        eventDatePop.timeZone = TimeZone.getTimeZone("EEST")

        val eventDateQstock = Calendar.getInstance()
        eventDateQstock[Calendar.YEAR] = 2023
        eventDateQstock[Calendar.MONTH] = 6
        eventDateQstock[Calendar.DAY_OF_MONTH] = 28
        eventDateQstock[Calendar.HOUR] = 0
        eventDateQstock[Calendar.MINUTE] = 0
        eventDateQstock[Calendar.SECOND] = 0
        eventDateQstock.timeZone = TimeZone.getTimeZone("EEST")

        val eventDateKotka = Calendar.getInstance()
        eventDateKotka[Calendar.YEAR] = 2023
        eventDateKotka[Calendar.MONTH] = 6
        eventDateKotka[Calendar.DAY_OF_MONTH] = 26
        eventDateKotka[Calendar.HOUR] = 0
        eventDateKotka[Calendar.MINUTE] = 0
        eventDateKotka[Calendar.SECOND] = 0
        eventDateKotka.timeZone = TimeZone.getTimeZone("EEST")

        val eventDateFlow = Calendar.getInstance()
        eventDateFlow[Calendar.YEAR] = 2023
        eventDateFlow[Calendar.MONTH] = 7
        eventDateFlow[Calendar.DAY_OF_MONTH] = 11
        eventDateFlow[Calendar.HOUR] = 0
        eventDateFlow[Calendar.MINUTE] = 0
        eventDateFlow[Calendar.SECOND] = 0
        eventDateFlow.timeZone = TimeZone.getTimeZone("EEST")

        val eventDateBlock = Calendar.getInstance()
        eventDateBlock[Calendar.YEAR] = 2023
        eventDateBlock[Calendar.MONTH] = 7
        eventDateBlock[Calendar.DAY_OF_MONTH] = 18
        eventDateBlock[Calendar.HOUR] = 0
        eventDateBlock[Calendar.MINUTE] = 0
        eventDateBlock[Calendar.SECOND] = 0
        eventDateBlock.timeZone = TimeZone.getTimeZone("EEST")

        val eventDateFolk = Calendar.getInstance()
        eventDateFolk[Calendar.YEAR] = 2023
        eventDateFolk[Calendar.MONTH] = 7
        eventDateFolk[Calendar.DAY_OF_MONTH] = 14
        eventDateFolk[Calendar.HOUR] = 0
        eventDateFolk[Calendar.MINUTE] = 0
        eventDateFolk[Calendar.SECOND] = 0
        eventDateFolk.timeZone = TimeZone.getTimeZone("EEST")

        val eventDateDown = Calendar.getInstance()
        eventDateDown[Calendar.YEAR] = 2023
        eventDateDown[Calendar.MONTH] = 7
        eventDateDown[Calendar.DAY_OF_MONTH] = 27
        eventDateDown[Calendar.HOUR] = 0
        eventDateDown[Calendar.MINUTE] = 0
        eventDateDown[Calendar.SECOND] = 0
        eventDateDown.timeZone = TimeZone.getTimeZone("EEST")

        //Find how many milliseconds until the event
        val diff1 = eventDateRock.timeInMillis - currentDate.timeInMillis
        // Change the milliseconds to days, hours, minutes, and seconds
        val days = diff1 / (24 * 60 * 60 * 1000)
        val hours  = diff1 / (1000 * 60 * 60) % 24
        val minutes = diff1 / (1000 * 60) % 60
        val seconds = (diff1 / 1000) % 60

        /*val diff2 = eventDateHanko.timeInMillis - currentDate.timeInMillis
        // Change the milliseconds to days, hours, minutes, and seconds
        val days2 = diff2 / (24 * 60 * 60 * 1000)
        val hours2 = diff2 / (1000 * 60 * 60) % 24
        val minutes2 = diff2 / (1000 * 60) % 60
        val seconds2 = (diff2 / 1000) % 60*/

        val diff3 = eventDateNummi.timeInMillis - currentDate.timeInMillis
        // Change the milliseconds to days, hours, minutes, and seconds
        val days3 = diff3 / (24 * 60 * 60 * 1000)
        val hours3  = diff3 / (1000 * 60 * 60) % 24
        val minutes3 = diff3 / (1000 * 60) % 60
        val seconds3 = (diff3 / 1000) % 60

        val diff4 = eventDateHimos.timeInMillis - currentDate.timeInMillis
        // Change the milliseconds to days, hours, minutes, and seconds
        val days4 = diff4 / (24 * 60 * 60 * 1000)
        val hours4  = diff4 / (1000 * 60 * 60) % 24
        val minutes4 = diff4 / (1000 * 60) % 60
        val seconds4 = (diff4 / 1000) % 60

        /*val diff5 = eventDateIskelma.timeInMillis - currentDate.timeInMillis
        // Change the milliseconds to days, hours, minutes, and seconds
        val days5 = diff5 / (24 * 60 * 60 * 1000)
        val hours5  = diff5 / (1000 * 60 * 60) % 24
        val minutes5 = diff5 / (1000 * 60) % 60
        val seconds5 = (diff5 / 1000) % 60*/

        val diff6 = eventDateProvinssi.timeInMillis - currentDate.timeInMillis
        // Change the milliseconds to days, hours, minutes, and seconds
        val days6 = diff6 / (24 * 60 * 60 * 1000)
        val hours6  = diff6 / (1000 * 60 * 60) % 24
        val minutes6 = diff6 / (1000 * 60) % 60
        val seconds6 = (diff6 / 1000) % 60

        val diff7 = eventDateTuska.timeInMillis - currentDate.timeInMillis
        // Change the milliseconds to days, hours, minutes, and seconds
        val days7 = diff7 / (24 * 60 * 60 * 1000)
        val hours7  = diff7 / (1000 * 60 * 60) % 24
        val minutes7 = diff7 / (1000 * 60) % 60
        val seconds7 = (diff7 / 1000) % 60

        val diff8 = eventDateRuis.timeInMillis - currentDate.timeInMillis
        // Change the milliseconds to days, hours, minutes, and seconds
        val days8 = diff8 / (24 * 60 * 60 * 1000)
        val hours8  = diff8 / (1000 * 60 * 60) % 24
        val minutes8 = diff8 / (1000 * 60) % 60
        val seconds8 = (diff8 / 1000) % 60

        /*val diff9 = eventDatePop.timeInMillis - currentDate.timeInMillis
        // Change the milliseconds to days, hours, minutes, and seconds
        val days9 = diff9 / (24 * 60 * 60 * 1000)
        val hours9  = diff9 / (1000 * 60 * 60) % 24
        val minutes9 = diff9 / (1000 * 60) % 60
        val seconds9 = (diff9 / 1000) % 60*/

        val diff10 = eventDateQstock.timeInMillis - currentDate.timeInMillis
        // Change the milliseconds to days, hours, minutes, and seconds
        val days10 = diff10 / (24 * 60 * 60 * 1000)
        val hours10  = diff10 / (1000 * 60 * 60) % 24
        val minutes10 = diff10 / (1000 * 60) % 60
        val seconds10 = (diff10 / 1000) % 60

        val diff11 = eventDateKotka.timeInMillis - currentDate.timeInMillis
        // Change the milliseconds to days, hours, minutes, and seconds
        val days11 = diff11 / (24 * 60 * 60 * 1000)
        val hours11  = diff11 / (1000 * 60 * 60) % 24
        val minutes11 = diff11 / (1000 * 60) % 60
        val seconds11 = (diff11 / 1000) % 60

        val diff12 = eventDateFlow.timeInMillis - currentDate.timeInMillis
        // Change the milliseconds to days, hours, minutes, and seconds
        val days12 = diff12 / (24 * 60 * 60 * 1000)
        val hours12  = diff12 / (1000 * 60 * 60) % 24
        val minutes12 = diff12 / (1000 * 60) % 60
        val seconds12 = (diff12 / 1000) % 60

        val diff13 = eventDateBlock.timeInMillis - currentDate.timeInMillis
        // Change the milliseconds to days, hours, minutes, and seconds
        val days13 = diff13 / (24 * 60 * 60 * 1000)
        val hours13  = diff13 / (1000 * 60 * 60) % 24
        val minutes13 = diff13 / (1000 * 60) % 60
        val seconds13 = (diff13 / 1000) % 60

        val diff14 = eventDateFolk.timeInMillis - currentDate.timeInMillis
        // Change the milliseconds to days, hours, minutes, and seconds
        val days14 = diff14 / (24 * 60 * 60 * 1000)
        val hours14  = diff14 / (1000 * 60 * 60) % 24
        val minutes14 = diff14 / (1000 * 60) % 60
        val seconds14 = (diff14 / 1000) % 60

        val diff15 = eventDateDown.timeInMillis - currentDate.timeInMillis
        // Change the milliseconds to days, hours, minutes, and seconds
        val days15 = diff15 / (24 * 60 * 60 * 1000)
        val hours15  = diff15 / (1000 * 60 * 60) % 24
        val minutes15 = diff15 / (1000 * 60) % 60
        val seconds15 = (diff15 / 1000) % 60


        // Display Countdown
        countdownTextRock.text = "${days}d ${hours}h ${minutes}m ${seconds}s"
        //countdownTextHanko.text = "${days2}d ${hours2}h ${minutes2}m ${seconds2}s"
        countdownTextNummi.text = "${days3}d ${hours3}h ${minutes3}m ${seconds3}s"
        countdownTextHimos.text = "${days4}d ${hours4}h ${minutes4}m ${seconds4}s"
        //countdownTextIskelma.text = "${days5}d ${hours5}h ${minutes5}m ${seconds5}s"
        countdownTextProvinssi.text = "${days6}d ${hours6}h ${minutes6}m ${seconds6}s"
        countdownTextTuska.text = "${days7}d ${hours7}h ${minutes7}m ${seconds7}s"
        countdownTextRuis.text = "${days8}d ${hours8}h ${minutes8}m ${seconds8}s"
        //countdownTextPop.text = "${days9}d ${hours9}h ${minutes9}m ${seconds9}s"
        countdownTextQstock.text = "${days10}d ${hours10}h ${minutes10}m ${seconds10}s"
        countdownTextKotka.text = "${days11}d ${hours11}h ${minutes11}m ${seconds11}s"
        countdownTextFlow.text = "${days12}d ${hours12}h ${minutes12}m ${seconds12}s"
        countdownTextBlock.text = "${days13}d ${hours13}h ${minutes13}m ${seconds13}s"
        countdownTextFolk.text = "${days14}d ${hours14}h ${minutes14}m ${seconds14}s"
        countdownTextDown.text = "${days15}d ${hours15}h ${minutes15}m ${seconds15}s"


    }


}


