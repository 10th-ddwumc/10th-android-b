package com.example.nike

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.nike.databinding.PocketFragmentBinding

class PocketFragment : Fragment(R.layout.pocket_fragment) {
    private lateinit var binding: PocketFragmentBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = PocketFragmentBinding.bind(view)

        binding.btorder.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_fragmentContainer, BuyFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}