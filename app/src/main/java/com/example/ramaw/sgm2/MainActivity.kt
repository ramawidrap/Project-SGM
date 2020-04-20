package com.example.ramaw.sgm2

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.ramaw.sgm2.Models.Produk
import android.util.Log
import com.example.ramaw.sgm2.Adapter.CustomAdapter
import com.google.firebase.database.*
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_susu_sgm.*
import kotlinx.android.synthetic.main.kategori_hadiah.*
import java.util.*
import android.R.attr.data
import android.R.attr.start
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.*
import com.example.ramaw.sgm2.Models.DataBaseHandler
import com.example.ramaw.sgm2.R.drawable.voucher
import com.google.zxing.integration.android.IntentResult
import java.io.ByteArrayOutputStream
import android.content.SharedPreferences
import android.net.Uri


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        val poin = getSharedPreferences("prefID", Context.MODE_PRIVATE)

        val point=poin.getInt("poin",0)

        var product:Produk?=null
        val listbitmap=ArrayList<Bitmap>()
        val produk=ArrayList<Produk>()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val kode=findViewById<LinearLayout>(R.id.kode)
        val scan=findViewById<LinearLayout>(R.id.scan)
        val lihatsemua= findViewById<TextView>(R.id.lihatsemua)
        val nutrivision=findViewById<ImageView>(R.id.nutrivision)
        val sgmnearby=findViewById<ImageView>(R.id.nutrinearby)
        val pointuser=findViewById<TextView>(R.id.pointuser)
        val banner=findViewById<ImageView>(R.id.banner)
        var id=20
        val priview= listOf("Susu","Voucher","Bestdeal","Perabotan")
        var random=Random().nextInt(4)
        var terpilih=priview.get(random)
        var db= DataBaseHandler(this)


        pointuser.text=point.toString()

        product=Produk(convertimage(R.drawable.produk2),7000,"Susu","SGM Aktif Presinutri 4 Plus","")
        db.insertData(product,1)

        product=Produk(convertimage(R.drawable.produk4),7500,"Susu","SGM Aktif Presinutri 400gr","")
        db.insertData(product,2)

        product=Produk(convertimage(R.drawable.produk4),9000,"Susu","SGM Aktif Presinutri 900gr","")
        db.insertData(product,3)

        product=Produk(convertimage(R.drawable.produk3),7500,"Susu","SGM EKsplor 3PLUS Buah dan Sayur 400gr","")
        db.insertData(product,4)

        product=Produk(convertimage(R.drawable.produk3),9000,"Susu","SGM EKsplor 3PLUS Buah dan Sayur 900gr","")
        db.insertData(product,5)

        product=Produk(convertimage(R.drawable.produk5),7500,"Susu","SGM Eksplor PHPro  400gr 1++","")
        db.insertData(product,6)

        product=Produk(convertimage(R.drawable.produk5),9000,"Susu","SGM Eksplor PHPro  900gr 1++","")
        db.insertData(product,7)




        //voucher
        product=Produk(convertimage(R.drawable.voucheralfa),7500,"Voucher","Voucher Alfamart","1. Voucher dapat digunakan untuk belanja produk apa saja di seluruh gerai Alfamart di Indonesia (tidak termasuk AlfaMidi, Lawson, AlfaCart)*2. Voucher tidak dapat digabungkan dengan voucher lainnya*3. 1 (satu) kode voucher hanya dapat digunakan untuk 1 (satu) kali transaksi*4. Jika total belanja lebih besar daripada jumlah voucher, maka kekurangan dapat dibayar tunai atau dengan kartu debit*5. Voucher dapat digunakan sebagai potongan saat belanja produk apa saja di Alfamart*6. Jika total belanja lebih kecil daripada nilai voucher, maka voucher tidak dapat ditukar atau dikembalikan dengan uang tunai")
        db.insertData(product,10)

        product=Produk(convertimage(R.drawable.vouchergopay),8000,"Voucher","Voucher Gopay Rp 50.000","1. Voucher hanya dapat digunakan untuk layanan Go-Ride.*2. Voucher hanya untuk 1 akun*3. Voucher tidak dapat diuangkan")
        db.insertData(product,11)

        product=Produk(convertimage(R.drawable.voucherpulsa),7500,"Voucher","Voucher Pulsa Rp 50.000","1. Pulsa hanya berlaku untuk pelanggan kartu prabayar.*2. Pengisian pulsa tidak memperpanjang masa aktif kartu prabayar.*3. Masa berlaku mulai aktif setelah pulsa dikirimkan ke pelanggan prabayar")
        db.insertData(product,12)

        product=Produk(convertimage(R.drawable.voucherpulsa),12500,"Voucher","Voucher Pulsa Rp 100.000","1. Pulsa hanya berlaku untuk pelanggan kartu prabayar.*2. Pengisian pulsa tidak memperpanjang masa aktif kartu prabayar.*3. Masa berlaku mulai aktif setelah pulsa dikirimkan ke pelanggan prabayar")
        db.insertData(product,13)

        product=Produk(convertimage(R.drawable.voucherlazada),10000,"Voucher","Voucher Belanja Lazada Rp 50.000","1. Voucher dapat digunakan untuk berbelanja seluruh produk (kecuali produk susu di bawah 1 tahun).*2.Tidak ada minimal pembelian untuk menggunakan kupon tsb.*3.Voucher ini hanya dapat di pakai 1 account untuk sekali pembelanjaan*4. Voucher tidak dapat di uangkan")
        db.insertData(product,14)

        //perabotan
        product=Produk(convertimage(R.drawable.panci),40000,"Perabotan","Set Panci","")
        db.insertData(product,15)

        product=Produk(convertimage(R.drawable.setrika),30000,"Perabotan","Setrika","")
        db.insertData(product,16)

        product=Produk(convertimage(R.drawable.blender),20000,"Perabotan","Blender","")
        db.insertData(product,17)

        product=Produk(convertimage(R.drawable.alatmakan),10000,"Perabotan","Alat Makan","")
        db.insertData(product,18)

        //Bestdeal
        product=Produk(convertimage(R.drawable.motor),100000,"Bestdeal","Motor Matic","1. Motor akan langsung dikirimkan ke alamat yang tertera*2. motor akan dikirimkan kurang lebih 2 bulan setelah poin ditukarkan*3. Motor merupakan keluaran original*4. Pengurusan nomor kendaraan akan menjadi tanggung jawab bunda")
        db.insertData(product,19)

        product=Produk(convertimage(R.drawable.voucherlazada),10000,"Bestdeal","Voucher Belanja Lazada Rp 50.000","1. Voucher dapat digunakan untuk berbelanja seluruh produk (kecuali produk susu di bawah 1 tahun).*2.Tidak ada minimal pembelian untuk menggunakan kupon tsb.*3.Voucher ini hanya dapat di pakai 1 account untuk sekali pembelanjaan*4. Voucher tidak dapat di uangkan")
        db.insertData(product,20)

        product=Produk(convertimage(R.drawable.alatmakan),10000,"Bestdeal","Alat Makan","")
        db.insertData(product,21)


        banner.setOnClickListener {
            val intent=Intent(Intent.ACTION_VIEW, Uri.parse("https://shopee.co.id/SGM-Eksplor-1plus-Madu-900-gr-Bundle-of-2-i.76746647.1285627640"))
            startActivity(intent)
        }




        Log.d("cekbitmap","masukgetdata")
        getData(terpilih)



        scan.setOnClickListener {
            val scann= IntentIntegrator(this)
            scann.initiateScan()
        }

        lihatsemua.setOnClickListener {
            val intent=Intent(this,Kategori_hadiah::class.java)
            startActivity(intent)
        }
        nutrivision.setOnClickListener {
            val intent=Intent(this,Nutrivision::class.java)
            startActivity(intent)
        }
        sgmnearby.setOnClickListener {
            val intent=Intent(this,SgmNearby::class.java)
            startActivity(intent)
        }
        kode.setOnClickListener{
            val dialog= Dialog(this)
            dialog.setContentView(R.layout.activity_input__kode)
            val enter=dialog.findViewById<Button>(R.id.enterkode)
            val inputcode=dialog.findViewById<EditText>(R.id.inputcode)
            dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()
            enter.setOnClickListener {
                if (inputcode.text.toString() == "sgm2018") {
                    val intent=Intent(this,ReedemKode::class.java)
                    startActivity(intent)
                }
                else{
                    inputcode.text=null
                }
            }

        }
        val database=FirebaseDatabase.getInstance().reference
        //susu



        val arrimage = ArrayList<Int>()
        arrimage.add(R.drawable.banneriklan4)
        arrimage.add(R.drawable.banneriklan3)

        val handler = Handler()
        val runnable = object : Runnable {
            internal var i = 0

            override fun run() {
                banner.setImageResource(arrimage.get(i))
                i++
                if (i > arrimage.size - 1) {
                    i = 0
                }
                handler.postDelayed(this, 3000)
            }
        }
        handler.postDelayed(runnable, 3000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK){
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (result != null) {
                if (result.contents == null) {
                    Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Scanned: " + result.contents, Toast.LENGTH_LONG).show()
                    if(result.contents == "sgm2018"){
                        val intent=Intent(this,ReedemKode::class.java)
                        startActivity(intent)
                    }
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }

    private fun getData(terpilih:String) {
        val db= DataBaseHandler(this)
        setupAdapter(db.readData(terpilih,"kategori"))

    }
    fun setupAdapter(data:ArrayList<Produk>){
        val recyclerView: RecyclerView = findViewById(R.id.listproduk)
        recyclerView.layoutManager = GridLayoutManager(this, 1,GridLayoutManager.HORIZONTAL,false)
        val adapter= CustomAdapter(data)
        recyclerView.adapter=adapter

    }
    fun convertimage(image : Int) : ByteArray{
        val bitmap = BitmapFactory.decodeResource(resources, image)
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG,90,stream)
        val imagesdb=stream.toByteArray()
        return imagesdb
    }

}
