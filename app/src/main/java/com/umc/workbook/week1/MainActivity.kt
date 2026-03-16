package com.umc.workbook.week1

import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.umc.workbook.week1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

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

        // 이미지 클릭 시 텍스트 색상 변경
        binding.ivYellow.setOnClickListener {
            binding.tvYellow.setTextColor(Color.parseColor("#FFEFB6"))
        }
        binding.ivBlue.setOnClickListener {
            binding.tvBlue.setTextColor(Color.parseColor("#CEE7F5"))
        }
        binding.ivPurple.setOnClickListener {
            binding.tvPurple.setTextColor(Color.parseColor("#BEC3ED"))
        }
        binding.ivGreen.setOnClickListener {
            binding.tvGreen.setTextColor(Color.parseColor("#B1D3B9"))
        }
        binding.ivRed.setOnClickListener {
            binding.tvRed.setTextColor(Color.parseColor("#EB8B8B"))
        }
    }
}