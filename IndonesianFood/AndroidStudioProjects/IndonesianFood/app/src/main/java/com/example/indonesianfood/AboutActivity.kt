package com.example.indonesianfood

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.about)

        // Menambahkan tombol kembali
        val btnBack: Button = findViewById(R.id.btn_back)
        btnBack.setOnClickListener {
            finish() // Ini akan menutup aktivitas saat ini dan kembali ke aktivitas sebelumnya
        }
    }
}