package com.example.ramaw.sgm2

import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.widget.CardView
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.transition.Slide
import android.transition.TransitionManager
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.ramaw.sgm2.Adapter.SusuSgmAdapter
import com.example.ramaw.sgm2.Models.Produk
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_susu_sgm.*
import kotlinx.android.synthetic.main.popup_layout.*
import android.widget.EditText
import com.example.ramaw.sgm2.Models.DataBaseHandler


class SusuSGM : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_susu_sgm)

        val btnback=findViewById<ImageView>(R.id.buttonback)

        val factory = layoutInflater

        val textEntryView = factory.inflate(R.layout.list_sususgm, null)

        val click = textEntryView.findViewById<LinearLayout>(R.id.linear)

        val kategori=intent.getStringExtra("kategori")

        getData(kategori)

        btnback.setOnClickListener {
            val intent= Intent(this,Kategori_hadiah::class.java)
            startActivity(intent)
        }








    }




    private fun getData(kategori : String) {
        val db= DataBaseHandler(this)
        setupAdapter(db.readData(kategori,"kategori"))
    }
    fun setupAdapter(data:ArrayList<Produk>){
        Log.d("testcuy","masuk setupadapter")
        val recyclerView: RecyclerView = findViewById<RecyclerView>(R.id.sususgm)
        recyclerView.layoutManager = GridLayoutManager(this,2)
        val adapter= SusuSgmAdapter(data)
        recyclerView.adapter=adapter



        }





    }





