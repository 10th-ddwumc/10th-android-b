package com.umc.workbook.week2.ui.shop.tops

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.umc.workbook.week2.databinding.FragmentTopsBinding

class TopsFragment : Fragment() {
    lateinit var binding : FragmentTopsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTopsBinding.inflate(inflater, container, false)
        return binding.root
    }

}