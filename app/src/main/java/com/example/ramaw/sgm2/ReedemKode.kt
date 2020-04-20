package com.example.ramaw.sgm2

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class ReedemKode : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reedem_kode)
        val poinredeem=findViewById<TextView>(R.id.poinreedem)
        val btnback=findViewById<ImageView>(R.id.buttonback)
        btnback.setOnClickListener {
            val intent= Intent(this,MainActivity::class.java)
            intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP )
            startActivity(intent)
        }
        val poinnambah=poinredeem.text.toString().toInt()
        val btnreedem=findViewById<Button>(R.id.btnreedem)
        btnreedem.setOnClickListener {


            val poin = getSharedPreferences("prefID", Context.MODE_PRIVATE)
            val point=poin.getInt("poin",0)
            val editor = poin.edit()
            editor.putInt("poin",point + poinnambah)
            editor.apply()



            val intent=Intent(this,MainActivity::class.java)
            intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP )
            startActivity(intent)
        }

    }
}
