package com.example.ramaw.sgm2.fragments


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView

import com.example.ramaw.sgm2.R
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.popup_layout.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class DailyNutritionFragments : android.support.v4.app.Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view=layoutInflater.inflate(R.layout.activity_nutrivision,null)
        val v=inflater.inflate(R.layout.fragment_daily_nutrition_fragments, container, false)

        val poin = v.context.getSharedPreferences("prefID", Context.MODE_PRIVATE)

        val berat=poin.getInt("weight",0)
        Log.d("cekbool",berat.toString())
        val tinggi=poin.getInt("height",0)
        val umur=poin.getInt("age",0)
        val gender=poin.getString("gender","banci")



        val caloriestext=v.findViewById<TextView>(R.id.caloriestext)
        val proteintext= v.findViewById<TextView>(R.id.proteintext)
        val fruitstext=v.findViewById<TextView>(R.id.fruitstext)
        val vegetablestext= v.findViewById<TextView>(R.id.vegetablestext)
        val grainstext=v.findViewById<TextView>(R.id.grainstext)
        val dairytext=v.findViewById<TextView>(R.id.dairytext)

        if (gender.equals("Male", ignoreCase = true)) {


            val kalori = ((10 * berat) + (6.25 * tinggi) - (5 * umur) + 5) * 1.55
            val protein = ((10 * berat) + (6.25 * tinggi) - (5 * umur) + 5) * 0.15
            val lemak = kalori * 0.2
            val karbo = kalori * 0.65
            Log.d("cekbool", "kalori " + kalori.toString())

            Log.d("cekbool", "cobasettext")
            caloriestext.text = kalori.toString()
            Log.d("cekbool", "settext")
            Log.d("cekbool", caloriestext.text.toString())

            if (umur >= 2 && umur <= 3) {
                dairytext.text = "2 Gram"
                proteintext.text = "2 Ounces"
                fruitstext.text = "1 Cup"
                vegetablestext.text = "3 Cup"
                grainstext.text = "2 Ounces"
            } else if (umur >= 4 && umur <= 8) {
                dairytext.text = "2,5 Gram"
                proteintext.text = "3,5 Ounces"
                fruitstext.text = "1 Cup"
                vegetablestext.text = "1,5 Cup"
                grainstext.text = "5 Ounces"
            } else if (umur >= 9 && umur <= 13) {
                dairytext.text = "3 Gram"
                proteintext.text = "5,75 Ounces"
                fruitstext.text = "1,75 Cup"
                vegetablestext.text = "2,75 Cup"
                grainstext.text = "7 Ounces"
            } else if (umur >= 14) {
                Log.d("cekbool", "setdairytext")
                dairytext.text = "3 Gram"
                proteintext.text = "6,5 Ounces"
                fruitstext.text = "2,25 Cup"
                vegetablestext.text = "3,25 Cup"
                grainstext.text = "8 Ounces"
                Log.d("cekbool", dairytext.text.toString())
            }





        }
        else if (gender.equals("Female", ignoreCase = true)) {
            val kalori = ((10 * berat) + (6.25 * tinggi) - (5 * umur) - 161) * 1.55

            caloriestext.text = kalori.toString()

            if (umur >= 2 && umur <= 3) {
                dairytext.text = "2 Gram"
                proteintext.text = "2 Ounces"
                fruitstext.text = "1 Cup"
                vegetablestext.text = "3 Cup"
                grainstext.text = "2 Ounces"
            } else if (umur >= 4 && umur <= 8) {
                dairytext.text = "2,5 Gram"
                proteintext.text = "3 Ounces"
                fruitstext.text = "1 Cup"
                vegetablestext.text = "1,5 Cup"
                grainstext.text = "4 Ounces"
            } else if (umur >= 9 && umur <= 13) {
                dairytext.text = "3 Gram"
                proteintext.text = "4 Ounces"
                fruitstext.text = "1,5 Cup"
                vegetablestext.text = "2,25 Cup"
                grainstext.text = "6 Ounces"
            } else if (umur >= 14) {
                dairytext.text = "3 Gram"
                proteintext.text = "5,75 Ounces"
                fruitstext.text = "1,75 Cup"
                vegetablestext.text = "2,75 Cup"
                grainstext.text = "7 Ounces"
            }


        }
        return v


    }


}
