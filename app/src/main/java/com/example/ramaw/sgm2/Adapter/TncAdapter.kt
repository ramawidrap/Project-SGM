package com.example.ramaw.sgm2.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.ramaw.sgm2.R

class TncAdapter(val listtc : List<String>) : RecyclerView.Adapter<TncAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TncAdapter.ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.tnc, p0, false)
        return TncAdapter.ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return listtc.size
    }

    override fun onBindViewHolder(p0: TncAdapter.ViewHolder, p1: Int) {
        p0.tnc.text=listtc.get(p1)
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val tnc=itemView.findViewById<TextView>(R.id.texttnc)
    }
}