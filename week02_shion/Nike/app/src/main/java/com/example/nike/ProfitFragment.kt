package com.example.nike

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.nike.databinding.ProfitFragmentBinding

class ProfitFragment : Fragment(R.layout.profit_fragment) {

    private lateinit var binding: ProfitFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = ProfitFragmentBinding.bind(view)

        binding.btRe.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_fragmentContainer, ReProfitFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}