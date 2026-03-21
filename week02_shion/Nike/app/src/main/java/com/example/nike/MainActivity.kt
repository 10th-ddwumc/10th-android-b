package com.example.nike

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nike.databinding.ActivityMainBinding
import java.time.DayOfWeek
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivityTag"
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "onCreate")

        //fragmnet 화면
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragmentContainer, HomeFragment())
            .commit()

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when(item.itemId){

                //홈 화면
                R.id.home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragmentContainer, HomeFragment())
                        .commit()
                    true
                }
                //구매화면
                R.id.buy -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragmentContainer, BuyFragmnet())
                        .commit()
                    true
                }
                //장바구니 화면
                R.id.pocket -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragmentContainer, PocketFragmnet())
                        .commit()
                    true
                }
                //위시리스트화면
                R.id.wishList -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragmentContainer, WishFragmnet())
                        .commit()
                    true
                }
                //프로필 화면
                R.id.profit -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragmentContainer, ProfitFragment())
                        .commit()
                    true
                }
                else -> false
        }

            }
        }
}