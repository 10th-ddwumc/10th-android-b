package ddwu.com.mobile.week02

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import ddwu.com.mobile.week02.databinding.FragmentPurchaseAllBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PurchaseAllFragment : Fragment() {

    private var _binding: FragmentPurchaseAllBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PurchaseViewModel by viewModels()
    private val productList = mutableListOf(
        ProductData("Nike Everyday Plus Cushioned", "US$10", R.drawable.everyday_cushioned, "Training Ankle Socks\n5 Colours"),
        ProductData("Nike Elite Crew", "US$16", R.drawable.elite_crew, "Basketball Socks\n7 Colours"),
        ProductData("Nike Air Force 1 '07", "US$115", R.drawable.airforce_women, "Women's Shoes\n5 Colours"),
        ProductData("Jordan ENike Air Force", "US$115", R.drawable.airforce_men, "Men's Shoes\n2 Colours")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPurchaseAllBinding.inflate(inflater, container, false)
        return binding.root
    }

    lateinit var adapter: ProductAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ProductAdapter(mutableListOf(),true){product,position ->
            viewModel.toggleHeart(requireContext(),product,position,adapter.productList)
        }
        binding.rvPurchase.adapter = adapter
        binding.rvPurchase.layoutManager = GridLayoutManager(requireContext(),2)

        viewModel.fetchPurchaseProducts(requireContext(),productList)

        lifecycleScope.launch{
            viewModel.purchaseProducts.collect { products ->
                adapter.updateList(products.toMutableList())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}