package com.example.loginandsignup

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class FestaritActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_festarit)

        val rockfestClick = findViewById<Button>(R.id.RockfestBtn)
        rockfestClick.setOnClickListener {
            val url = "https://rockfest.fi/"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

        val hankoClick = findViewById<Button>(R.id.HankoBtn)
        hankoClick.setOnClickListener {
            val url = "https://hankosommarfest.fi/"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

        val nummiClick = findViewById<Button>(R.id.NummirockBtn)
        nummiClick.setOnClickListener {
            val url = "https://www.nummirock.fi/"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

        val himoClick = findViewById<Button>(R.id.HimosBtn)
        himoClick.setOnClickListener {
            val url = "https://himosjuhannus.fi/"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

        val iskelmaClick = findViewById<Button>(R.id.IskelmaBtn)
        iskelmaClick.setOnClickListener {
            val url = "https://iskelmafestivaali.fi/"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

        val provinssiClick = findViewById<Button>(R.id.ProvinssiBtn)
        provinssiClick.setOnClickListener {
            val url = "https://www.provinssi.fi/"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

        val tuskaClick = findViewById<Button>(R.id.TuskaBtn)
        tuskaClick.setOnClickListener {
            val url = "https://www.tuska.fi/"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

        val ruisClick = findViewById<Button>(R.id.RuisBtn)
        ruisClick.setOnClickListener {
            val url = "https://ruisrock.fi/"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

        val suomiClick = findViewById<Button>(R.id.SuomipopBtn)
        suomiClick.setOnClickListener {
            val url = "https://suomipopfestivaali.fi/"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

        val qstockClick = findViewById<Button>(R.id.QstockBtn)
        qstockClick.setOnClickListener {
            val url = "https://qstock.fi/"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

        val kotkaClick = findViewById<Button>(R.id.KotkanBtn)
        kotkaClick.setOnClickListener {
            val url = "https://meripaivat.com/"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

        val flowClick = findViewById<Button>(R.id.FlowBtn)
        flowClick.setOnClickListener {
            val url = "https://www.flowfestival.com/"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

        val blockClick = findViewById<Button>(R.id.BlockBtn)
        blockClick.setOnClickListener {
            val url = "https://www.blockfest.fi/"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

    }

}