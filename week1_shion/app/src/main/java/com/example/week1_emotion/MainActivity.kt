package com.example.week1_emotion

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val imageHappy = findViewById<ImageView>(R.id.imageHappy)
        val tvHappy = findViewById<TextView>(R.id.tvHappy)

        val imgExcited = findViewById<ImageView>(R.id.imgExcited)
        val tvExcited = findViewById<TextView>(R.id.tvExcited)

        val imgNormal = findViewById<ImageView>(R.id.imgNormal)
        val tvNormal = findViewById<TextView>(R.id.tvNormal)

        val imgAnxious = findViewById<ImageView>(R.id.imgAnxious)
        val tvAnxious = findViewById<TextView>(R.id.tvAnxious)

        val imgAngry = findViewById<ImageView>(R.id.imgAngry)
        val tvAngry = findViewById<TextView>(R.id.tvAngry)

        imageHappy.setOnClickListener {
            //tvHappy.visibility = View.VISIBLE
            tvHappy.setTextColor(Color.YELLOW)
        }

        imgExcited.setOnClickListener {
            //tvExcited.visibility = View.VISIBLE
            tvExcited.setTextColor(Color.BLUE)
        }

        imgNormal.setOnClickListener {
            //tvNormal.visibility = View.VISIBLE
            tvNormal.setTextColor(Color.MAGENTA)
        }

        imgAnxious.setOnClickListener {
            //tvAnxious.visibility = View.VISIBLE
            tvAnxious.setTextColor(Color.GREEN)
        }

        imgAngry.setOnClickListener {
            //tvAngry.visibility = View.VISIBLE
            tvAngry.setTextColor(Color.RED)
        }
    }
}