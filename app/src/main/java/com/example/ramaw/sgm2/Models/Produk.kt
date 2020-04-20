package com.example.ramaw.sgm2.Models

import android.graphics.Bitmap

class Produk {
    var image:ByteArray = ByteArray(0)
    var poin:Int=0
    var kategori:String=""
    var nama:String=""
    var tc= ""
    constructor(image:ByteArray,poin:Int,kategori:String,nama:String,tc:String){
        this.image=image
        this.poin=poin
        this.kategori=kategori
        this.nama=nama
        this.tc=tc
    }
    constructor(){

    }
}