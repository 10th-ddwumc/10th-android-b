package com.umc.workbook.week2.ui.shop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.umc.workbook.week2.R
import com.umc.workbook.week2.databinding.FragmentSaleBinding

class SaleFragment : Fragment() {

    lateinit var binding: FragmentSaleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSaleBinding.inflate(inflater, container, false)
        return binding.root
    }

}