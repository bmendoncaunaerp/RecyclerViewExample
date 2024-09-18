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

        val itemList = listOf(
            Pessoa("Pessoa 1", "https://br.trace.tv/wp-content/uploads/2023/12/01-10-2.png"),
            Pessoa("Pessoa 2", "https://br.trace.tv/wp-content/uploads/2023/12/01-10-2.png"),
            Pessoa("Pessoa 3", "https://br.trace.tv/wp-content/uploads/2023/12/01-10-2.png"),
            Pessoa("Pessoa 4", "https://br.trace.tv/wp-content/uploads/2023/12/01-10-2.png"),
        )

        val adapter = PessoaAdapter(itemList, ::onListItemClicked)
        val layoutManager = LinearLayoutManager(this)

        binding.recyclerViewItems.adapter = adapter
        binding.recyclerViewItems.layoutManager = layoutManager
    }

    private fun onListItemClicked(pessoa: Pessoa) {
        Toast.makeText(this, pessoa.toString(), Toast.LENGTH_LONG).show()
    }
}