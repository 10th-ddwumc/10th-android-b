package ddwu.com.mobile.week02

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import ddwu.com.mobile.week02.databinding.FragmentPurchaseBinding

class PurchaseFragment : Fragment() {

    private var _binding: FragmentPurchaseBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPurchaseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productList = mutableListOf(
            ProductData("Nike Everyday Plus Cushioned", "US$10", R.drawable.everyday_cushioned, "Training Ankle Socks (6 Pairs)\n5 Colours"),
            ProductData("Nike Elite Crew", "US$16", R.drawable.elite_crew, "Basketball Socks\n7 Colours"),
            ProductData("Nike Air Force 1'07", "US$115", R.drawable.airforce_women, "Women's Shoes\n5 Colours"),
            ProductData("Jordan ENike Air Force\n1'07ssentials", "US$115", R.drawable.airforce_men, "Men's Shoes\n2 Colours")
        )

        val adapter = ProductAdapter(productList)
        binding.rvPurchase.adapter = adapter
        binding.rvPurchase.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}