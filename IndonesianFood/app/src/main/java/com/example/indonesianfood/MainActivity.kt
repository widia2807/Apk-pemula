package com.example.indonesianfood

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button // Import Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    private lateinit var rvFood: RecyclerView
    private lateinit var btnAbout: Button // Deklarasi Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi RecyclerView dengan ID yang benar
        rvFood = findViewById(R.id.rvfood)

        // Inisialisasi Button
        btnAbout = findViewById(R.id.btn_about) // Pastikan ID ini sesuai dengan layout Anda

        // Ambil data makanan
        val list = getListFood()

        // Tampilkan data makanan di RecyclerView
        showRecyclerList(list)

        // Tambahkan click listener untuk tombol About
        btnAbout.setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                rvFood.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                rvFood.layoutManager = GridLayoutManager(this, 2)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListFood(): ArrayList<Food> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listFood = ArrayList<Food>()

        for (i in dataName.indices) {
            val food = Food(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listFood.add(food)
        }

        // Jangan lupa recycle TypedArray
        dataPhoto.recycle()

        return listFood
    }

    private fun showRecyclerList(list: ArrayList<Food>) {
        rvFood.layoutManager = LinearLayoutManager(this)
        val adapter = ListFoodAdapter(list)
        rvFood.adapter = adapter

        // Tambahkan klik listener di adapter
        adapter.setOnItemClickCallback(object : ListFoodAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Food) {
                showSelectedFood(data) // Kirim data ke DetailActivity
            }
        })
    }

    // Fungsi untuk memulai DetailActivity dan mengirim data
    private fun showSelectedFood(selectedFood: Food) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.KEY_PLACE, selectedFood) // Kirim data Food
        startActivity(intent)
    }
}
