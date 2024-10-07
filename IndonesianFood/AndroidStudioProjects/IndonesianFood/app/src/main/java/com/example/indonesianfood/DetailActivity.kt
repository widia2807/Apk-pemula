package com.example.indonesianfood

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    companion object {
        const val KEY_PLACE = "key_place" // Declare the key for intent extra
    }

    @SuppressLint("ObsoleteSdkInt")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.food_detail_activity)

        // Menerima data yang dikirim dari MainActivity
        val dataFood: Food? = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(KEY_PLACE, Food::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Food>(KEY_PLACE)
        }

        // Tampilkan atau gunakan dataFood di sini
        dataFood?.let {
            // Menghubungkan elemen UI dengan ID yang benar
            val imgPhoto: ImageView = findViewById(R.id.img_item_photo)
            val tvName: TextView = findViewById(R.id.tv_item_name)
            val tvDescription: TextView = findViewById(R.id.tv_item_description)

            // Mengisi data ke elemen UI
            imgPhoto.setImageResource(it.photo)
            tvName.text = it.name
            tvDescription.text = it.description
        }

        // Menambahkan tombol kembali
        val btnBack: Button = findViewById(R.id.btn_back)
        btnBack.setOnClickListener {
            finish() // Ini akan menutup aktivitas saat ini dan kembali ke aktivitas sebelumnya
        }
    }
}