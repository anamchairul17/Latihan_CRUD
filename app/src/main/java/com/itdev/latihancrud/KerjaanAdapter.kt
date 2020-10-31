package com.itdev.latihancrud

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class KerjaanAdapter (val mCtx : Context, val layoutResId : Int, val krjList :List<DataWarga>) : ArrayAdapter<DataWarga>(mCtx, layoutResId, krjList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater : LayoutInflater = LayoutInflater.from(mCtx)

        val view :  View = layoutInflater.inflate(layoutResId, null)

        val tvNamaKerjaan = view.findViewById<TextView>(R.id.tv_kerjaan)
        val tvUmur = view.findViewById<TextView>(R.id.tv_umur)

        val kerjaan : DataWarga = krjList[position]

        tvNamaKerjaan.text = kerjaan.nama
        tvUmur.text = kerjaan.umur.toString()

        return view
    }
}

