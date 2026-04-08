package com.umc.workbook.week2

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.umc.workbook.week2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val TAG = "LIFE_QUIZ"

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
//        Log.d(TAG, "onCreate")

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, HomeFragment())
            .commit()

        // Fragment 변경
        binding.mainBnv.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.homeFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragment_container, HomeFragment())
                        .commit()
                    true
                }
                R.id.shopFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragment_container, ShopFragment())
                        .commit()
                    true
                }
                R.id.wishlistFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragment_container, WishlistFragment())
                        .commit()
                    true
                }
                R.id.cartFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragment_container, CartFragment())
                        .commit()
                    true
                }
                R.id.profileFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragment_container, ProfileFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }

    // 미션 1
//    override fun onStart() {
//        super.onStart()
//        Log.d(TAG, "onStart")
//    }
//    override fun onResume() {
//        super.onResume()
//        Log.d(TAG, "onResume")
//    }
//    override fun onPause() {
//        super.onPause()
//        Log.d(TAG, "onPause")
//    }
//    override fun onStop() {
//        super.onStop()
//        Log.d(TAG, "onStop")
//    }
//    override fun onDestroy() {
//        super.onDestroy()
//        Log.d(TAG, "onDestroy")
//    }
//    override fun onRestart() {
//        super.onRestart()
//        Log.d(TAG, "onRestart")
//    }

}