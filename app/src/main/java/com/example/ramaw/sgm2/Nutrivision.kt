package com.example.ramaw.sgm2

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.content.Intent
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.content.ContextCompat.getSystemService
import android.view.LayoutInflater
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.Log
import android.widget.*
import com.example.ramaw.sgm2.Adapter.ViewPagerAdapter
import com.example.ramaw.sgm2.R.id.*
import com.example.ramaw.sgm2.fragments.DailyNutritionFragments
import com.example.ramaw.sgm2.fragments.NutrivisionPlanningFragments
import kotlinx.android.synthetic.main.activity_nutrivision.view.*
import kotlinx.android.synthetic.main.fragment_daily_nutrition_fragments.*
import org.w3c.dom.Text








class Nutrivision : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        var pagerAdapter:ViewPagerAdapter?=null
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nutrivision)

        val submit=findViewById<Button>(R.id.submit)
        val nutripoint=findViewById<ImageView>(R.id.sgmpoint)
        val sgmpoint=findViewById<ImageView>(R.id.sgmpoint)
        val sgmnearby=findViewById<ImageView>(R.id.sgmnearby)
        val viewpager=findViewById<ViewPager>(R.id.viewpager)
        val tablayout=findViewById<TabLayout>(R.id.tablayout)
        val ngisikosong=findViewById<RelativeLayout>(R.id.ngisikosong)
        val namabayi=findViewById<EditText>(R.id.namabayi)
        val edit=findViewById<TextView>(R.id.edit)
        val gender=findViewById<Spinner>(R.id.gender)
        val weight=findViewById<EditText>(R.id.weight)
        val height=findViewById<EditText>(R.id.height)
        val age=findViewById<EditText>(R.id.age)







        pagerAdapter= ViewPagerAdapter(supportFragmentManager)
        pagerAdapter!!.addFragments(DailyNutritionFragments(),"")
        pagerAdapter!!.addFragments(NutrivisionPlanningFragments(),"")

        viewpager.adapter=pagerAdapter
        tablayout.setupWithViewPager(viewpager,true)

        gender.setEnabled(false)
        var gendertext=""

        val genderlist= arrayListOf<String>("-","Male","Female")
        val adapter=ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,genderlist)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        gender.setAdapter(adapter)

        gender.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.d("spinner","kosong")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(genderlist.get(position) == "Male"){
                    gendertext="Male"
                }
                else if(genderlist.get(position) == "Female"){
                    gendertext="Female"
                }
                else{
                    gendertext="-"
                }
            }

        }









        submit.setOnClickListener {
            if(!namabayi.text.toString().isEmpty() && gendertext != "-" && !weight.text.toString().isEmpty() && !height.text.toString().isEmpty()&& !age.text.toString().isEmpty()) {
                val poin = getSharedPreferences("prefID", Context.MODE_PRIVATE)
                val editor = poin.edit()
                editor.putString("gender",gendertext)
                editor.putInt("weight",weight.text.toString().toInt())
                editor.putInt("height",height.text.toString().toInt())
                editor.putInt("age",age.text.toString().toInt())
                editor.apply()
                ngisikosong.setVisibility(View.GONE)
                viewpager.setVisibility(View.VISIBLE)
                tablayout.setVisibility(View.VISIBLE)
                namabayi.setEnabled(false)
                gender.setEnabled(false)
                weight.setEnabled(false)
                height.setEnabled(false)
                age.setEnabled(false)


            }



        }
        edit.setOnClickListener {
            namabayi.setEnabled(true)
            gender.isClickable=true
            gender.setEnabled(true)
            weight.setEnabled(true)
            height.setEnabled(true)
            age.setEnabled(true)
        }
        sgmpoint.setOnClickListener {
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        sgmnearby.setOnClickListener {
            val intent=Intent(this,SgmNearby::class.java)
            startActivity(intent)
            finish()
        }






    }


}
