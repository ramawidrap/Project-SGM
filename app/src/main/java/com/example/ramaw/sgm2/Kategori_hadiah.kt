package com.example.ramaw.sgm2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout

class Kategori_hadiah : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kategori_hadiah)

        val bestdealview = findViewById<LinearLayout>(R.id.bestdealview)
        val sgmview = findViewById<LinearLayout>(R.id.sgmview)
        val perabotanview = findViewById<LinearLayout>(R.id.perabotanview)
        val voucherview = findViewById<LinearLayout>(R.id.voucherview)
        val btnback=findViewById<ImageView>(R.id.buttonback)

        sgmview.setOnClickListener {
            val intent = Intent(this, SusuSGM::class.java)
            intent.putExtra("kategori", "Susu")
            startActivity(intent)
        }
        voucherview.setOnClickListener {
            val intent = Intent(this, SusuSGM::class.java)
            intent.putExtra("kategori", "Voucher")
            startActivity(intent)
        }
        perabotanview.setOnClickListener {
            val intent = Intent(this, SusuSGM::class.java)
            intent.putExtra("kategori", "Perabotan")
            startActivity(intent)
        }
        bestdealview.setOnClickListener {
            val intent = Intent(this, SusuSGM::class.java)
            intent.putExtra("kategori", "Bestdeal")
            startActivity(intent)
        }
        btnback.setOnClickListener {
            val intent=Intent(this,MainActivity::class.java)
            intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP )
            startActivity(intent)
        }

    }
}
