package com.example.nationalmuseum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_NationalMuseum)
        setContentView(R.layout.activity_main)

        val btnCollection = findViewById<Button>(R.id.btnCollection)
        btnCollection.setOnClickListener {
            val intent = Intent(this, MyCollection::class.java)
            startActivity(intent)
        }
    }
}