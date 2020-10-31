package com.itdev.latihancrud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.database.*

class AddOccupationActivity : AppCompatActivity() {

    private lateinit var tvNama : TextView
    private lateinit var etPekerjaan : EditText
    private lateinit var etUmur : EditText
    private lateinit var btnTambah : Button
    private lateinit var lvdataWarga : ListView
    private lateinit var datawargaList : MutableList<DataWarga>
    private lateinit var ref : DatabaseReference

    companion object{
        const val EXTRA_NAMA = "extra_nama"
        const val EXTRA_ID = "extra_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_occupation)

        val id = intent.getStringExtra(EXTRA_ID)
        val nama = intent.getStringExtra(EXTRA_NAMA)
        datawargaList = mutableListOf()

        ref = FirebaseDatabase.getInstance().getReference("data_warga").child(id!!)

        tvNama = findViewById(R.id.tv_nama)
        etPekerjaan = findViewById(R.id.et_pekerjaan)
        etUmur = findViewById(R.id.et_umur)
        btnTambah = findViewById(R.id.btn_add_data)
        lvdataWarga = findViewById(R.id.lv_data_warga)

        btnTambah.setOnClickListener {
            saveDataWarga()
        }
    }

    fun saveDataWarga(){
        val namaPekerjaan = etPekerjaan.text.toString().trim()
        val umurText = etUmur.text.toString().trim()
        val umur = umurText.toInt()

            if(namaPekerjaan.isEmpty()){
            etPekerjaan.error = "Cari kerja jangan nganggur!!!"
            return
            }
            if(umurText.isEmpty()){
            etUmur.error = "Inget Umur udah TUA!!!"
            return
            }

            val kerjaId = ref.push().key
            val kerja = DataWarga(kerjaId!!, namaPekerjaan, umur)

        ref.child(kerjaId).setValue(kerja).addOnCompleteListener {
            Toast.makeText(applicationContext, "Data berhasil di tambahkan", Toast.LENGTH_SHORT).show()
        }

        ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    datawargaList.clear()
                    for (h in snapshot.children){
                        val kerjaan = h.getValue(DataWarga::class.java)
                        if (kerjaan != null) {
                            datawargaList.add(kerjaan)
                        }
                    }

                    val adapter = KerjaanAdapter(this@AddOccupationActivity, R.layout.item_kerjaan, datawargaList)
                    lvdataWarga.adapter = adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}
