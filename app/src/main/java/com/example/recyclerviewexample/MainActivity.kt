package com.example.recyclerviewexample

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val itemList: MutableList<ItemData?> = MutableList(200) { null }
        val adapter = ItemAdapter(itemList, ::onListItemClicked)
        val layoutManager = LinearLayoutManager(this)

        binding.recyclerViewItems.adapter = adapter
        binding.recyclerViewItems.layoutManager = layoutManager
    }

    private fun onListItemClicked(itemData: ItemData) {
        Toast.makeText(this, itemData.toString(), Toast.LENGTH_LONG).show()
    }
}