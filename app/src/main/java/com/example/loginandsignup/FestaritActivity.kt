package com.example.loginandsignup

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class FestaritActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_festarit)

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

}