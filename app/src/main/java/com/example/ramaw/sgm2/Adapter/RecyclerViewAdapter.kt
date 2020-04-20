package com.example.ramaw.sgm2.Adapter
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.ramaw.sgm2.MainActivity
import com.example.ramaw.sgm2.Models.DataBaseHandler
import com.example.ramaw.sgm2.Models.Produk
import com.example.ramaw.sgm2.R
import com.example.ramaw.sgm2.R.id.poinsgm
import com.example.ramaw.sgm2.R.id.pointukar

class CustomAdapter(val listproduk:ArrayList<Produk>): RecyclerView.Adapter<CustomAdapter.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        Log.d("cekbitmap","kepanggil gak")
        val v = LayoutInflater.from(p0.context).inflate(R.layout.list_recyclerview, p0, false)
        return ViewHolder(v)//To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        return listproduk.size //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val produk=listproduk[p1]
        Log.d("cekbitmap","masukgak")
        var bitmapimage: Bitmap = BitmapFactory.decodeByteArray(produk.image,0,produk.image.size)
        Log.d("cekbitmap",bitmapimage.toString())
        p0.imageview.setImageBitmap(bitmapimage)
        p0.textview.text=produk.poin.toString()
        p0.namasgm.text=produk.nama//To change body of created functions use File | Settings | File Templates.
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textview =  itemView.findViewById<TextView>(R.id.textproduk)
        val imageview = itemView.findViewById<ImageView>(R.id.imageproduk)
        val namasgm=itemView.findViewById<TextView>(R.id.namasgm)

        val db= DataBaseHandler(itemView.rootView.context)





        val dialog= Dialog(itemView.rootView.context)
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
            poin.text = (textview.text.toString())
            title.text = (namasgm.text.toString())
            listtnc.layoutManager= LinearLayoutManager(itemView.context)
            val read=db.readData(namasgm.text.toString(),"nama")
            val size=read.size
            if(read.get(0).tc.length==0){
                titletnc.setVisibility(View.GONE)
            }

            if(poinuser < poin.text.toString().toInt()){
                btntukar.isEnabled=false
                btntukar.isClickable=false
                btntukar.setBackgroundResource(R.color.bgbody)
            }


            btntukar.setOnClickListener {
                val editor=spref.edit()
                editor.putInt("poin",poinuser - poin.text.toString().toInt())
                editor.apply()
                val intent= Intent(itemView.context, MainActivity::class.java)
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
    }


}