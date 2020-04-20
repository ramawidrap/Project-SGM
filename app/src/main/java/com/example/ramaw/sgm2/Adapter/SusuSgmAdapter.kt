package com.example.ramaw.sgm2.Adapter

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat.getSystemService
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.transition.Slide
import android.transition.TransitionManager
import android.transition.Visibility
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.ramaw.sgm2.*
import com.example.ramaw.sgm2.Models.Produk
import com.example.ramaw.sgm2.R.layout.popup_layout
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_susu_sgm.*
import kotlinx.android.synthetic.main.activity_susu_sgm.view.*
import android.widget.EditText
import com.example.ramaw.sgm2.Models.DataBaseHandler
import com.example.ramaw.sgm2.R.id.*


class SusuSgmAdapter(val listsususgm:ArrayList<Produk>): RecyclerView.Adapter<SusuSgmAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {

        val v = LayoutInflater.from(p0.context).inflate(R.layout.list_sususgm, p0, false)
        return ViewHolder(v)//To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        return listsususgm.size //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val produk = listsususgm[p1]
        var bitmapimage: Bitmap = BitmapFactory.decodeByteArray(produk.image,0,produk.image.size)
        p0.imagesgm.setImageBitmap(bitmapimage)
        p0.namasgm.text = produk.nama
        p0.poinsgm.text = produk.poin.toString()//To change body of created functions use File | Settings | File Templates.
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val namasgm = itemView.findViewById<TextView>(R.id.namaproduk)
        val poinsgm = itemView.findViewById<TextView>(R.id.poinsgm)
        val imagesgm = itemView.findViewById<ImageView>(R.id.imagesgm)
        val db= DataBaseHandler(itemView.rootView.context)





        val dialog=Dialog(itemView.rootView.context)
        val click = itemView.setOnClickListener {

            val spref = itemView.context.getSharedPreferences("prefID", Context.MODE_PRIVATE)
            val poinuser=spref.getInt("poin",0)

            Log.d("cardviewclick", "testbrooo")
            Log.d("cardviewclick", namasgm.text.toString())

            val vi=LayoutInflater.from(itemView.context).inflate(R.layout.popup_layout,null)


            dialog.setContentView(R.layout.popup_layout)
            val listtnc=dialog.findViewById<RecyclerView>(R.id.tnc)
            val title=dialog.findViewById<TextView>(R.id.titlereedem)
            val poin=dialog.findViewById<TextView>(R.id.pointukar)
            val titletnc=dialog.findViewById<TextView>(R.id.tnctitle)
            val btntukar=dialog.findViewById<ConstraintLayout>(R.id.buttontukar)
            val btndismiss=dialog.findViewById<ImageView>(R.id.buttondismiss)
            poin.text = (poinsgm.text.toString())
            title.text = (namasgm.text.toString())
            listtnc.layoutManager=LinearLayoutManager(itemView.context)
            val read=db.readData(namasgm.text.toString(),"nama")
            val size=read.size
            if(read.get(0).tc.length==0){
                titletnc.setVisibility(View.GONE)
            }
            if(poinuser < poin.text.toString().toInt()){
                btntukar.isEnabled = false
                btntukar.isClickable=false
                btntukar.setBackgroundResource(R.color.bgbody)
            }

            btntukar.setOnClickListener {
                val editor=spref.edit()
                editor.putInt("poin",poinuser - poin.text.toString().toInt())
                editor.apply()
                val intent=Intent(itemView.context,MainActivity::class.java)
                intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP )
                itemView.context.startActivity(intent)
            }
            Log.d("cardviewclick",read.toString())
            val adapter=TncAdapter(read.get(0).tc.split("*"))
            Log.d("cardviewclick",read.get(0).tc)
            listtnc.adapter=adapter

            dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()

            btndismiss.setOnClickListener {
                dialog.dismiss()
            }


        }

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {


                val toReturn: ArrayList<Produk> = ArrayList();

                for (data in dataSnapshot.children) {
                    val produkSgm = data.getValue<Produk>(Produk::class.java)
                    Log.d("cardviewclick", data.child("poin").getValue().toString())

                    //unwrap
                    val produk = produkSgm.let { it } ?: continue

                    toReturn.add(produk)
                }


            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.d("Erorboy", "eror")
            }

        }

        val database = FirebaseDatabase.getInstance().getReference().child("Produk").child("Susu").addValueEventListener(postListener)


    }
}


