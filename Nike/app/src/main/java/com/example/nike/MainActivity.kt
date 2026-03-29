package com.example.nike

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.nike.databinding.ActivityMainBinding

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
                        .replace(R.id.main_fragmentContainer, BuyFragment())
                        .commit()
                    true
                }
                //장바구니 화면
                R.id.pocket -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragmentContainer, PocketFragment())
                        .commit()
                    true
                }
                //위시리스트화면
                R.id.wishList -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragmentContainer, WishFragment())
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