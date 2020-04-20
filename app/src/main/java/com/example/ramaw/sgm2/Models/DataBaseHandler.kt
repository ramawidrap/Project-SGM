package com.example.ramaw.sgm2.Models

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast

val DATABASE_NAME="MyDB"
val TABLE_NAME="Produk"
val COL_IMAGE="image"
val COL_POIN="poin"
val COL_KATEGORI="kategori"
val COL_NAMA="nama"
val COL_TC="tc"
val COL_ID="id"



class DataBaseHandler(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null,2) {
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE " + TABLE_NAME + "(" +
                COL_ID + " INT PRIMARY KEY, "+
                COL_NAMA + " TEXT, " +
                COL_POIN + " TEXT, " +
                COL_KATEGORI + " TEXT, " +
                COL_TC + " TEXT, " +
                COL_IMAGE + " BLOB" + ")")
        db.execSQL(createTable)
    }

    fun insertData(produk: Produk,int :Int) {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_ID,int)
        cv.put(COL_IMAGE, produk.image)
        cv.put(COL_POIN, produk.poin)
        cv.put(COL_KATEGORI, produk.kategori)
        cv.put(COL_NAMA, produk.nama)
        cv.put(COL_TC, produk.tc)
        var result = db.insert(TABLE_NAME, null, cv)
        Log.d("insertdatabase", "cek masuk")
        db.close()
        if (result == -1.toLong()) {
            Log.d("insertdatabase", "failed")
        } else
            Log.d("insertdatabase", "success")
    }

    fun readData(input: String, condition: String): ArrayList<Produk> {
        var list = ArrayList<Produk>()
        val db = this.readableDatabase
        Log.d("haha", "cek berhasil readdataga")
        val query = "SELECT * FROM " + TABLE_NAME + " WHERE " + condition + " = " + "'" + input + "'"
        var result = db.rawQuery(query, null)
        while (result.moveToNext()) {
            var produk = Produk()
            Log.d("cekcolumn",result.getColumnIndex(COL_IMAGE).toString())
            produk.image = result.getBlob(result.getColumnIndex(COL_IMAGE))
            produk.poin = result.getInt(result.getColumnIndex(COL_POIN))
            produk.nama = result.getString(result.getColumnIndex(COL_NAMA)).toString()
            produk.tc = result.getString(result.getColumnIndex(COL_TC)).toString()
            list.add(produk)
            Log.d("haha", "berhasilread"+input)

        }
        Log.d("haha", "berhasilsemua")
        result.close()
        db.close()
        return list
    }
    fun updateData(input:String,condition:String,column:String,update:String){
        Log.d("test","masuk update gaaa")
        val db=this.writableDatabase
        var cv=ContentValues()
        val query="SELECT * FROM "+ TABLE_NAME +" WHERE "+ condition +" = "+"'"+input+"'"
        Log.d("test","masuk update gaaatuhhhh")
        val result = db.rawQuery(query,null)

        if(result.moveToFirst()){
            Log.d("test","masuk updatedata")
            cv.put(column,update)
            Log.d("test","test bitmapppppppppp")
            db.update(TABLE_NAME,cv, condition+" = "+"'"+input+"'",null)
        }
        result.close()
        db.close()
    }
}