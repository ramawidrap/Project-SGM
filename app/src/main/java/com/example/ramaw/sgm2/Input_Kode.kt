package com.example.ramaw.sgm2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Input_Kode : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input__kode)


        val enterkode=findViewById<Button>(R.id.enterkode)

        enterkode.setOnClickListener {
            val intent= Intent(this,ReedemKode::class.java)
            startActivity(intent)
        }
    }
}
