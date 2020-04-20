package com.example.ramaw.sgm2

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.RelativeLayout

class Splash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val poin = getSharedPreferences("prefID", Context.MODE_PRIVATE)
        val editor = poin.edit()
        editor.putInt("poin",10000)
        editor.apply()

        val handler= Handler()
        handler.postDelayed({
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()

            
        },2000)




    }

}
