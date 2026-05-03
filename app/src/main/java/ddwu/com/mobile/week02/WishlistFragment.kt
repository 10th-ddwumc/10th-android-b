package ddwu.com.mobile.week02

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.preferences.core.preferencesOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import ddwu.com.mobile.week02.databinding.FragmentWishlistBinding
import kotlinx.coroutines.launch import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WishlistFragment : Fragment() {

    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding!!
    private val viewModel: WishlistViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWishlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lateinit var adapter : ProductAdapter
        adapter = ProductAdapter(mutableListOf(),true) { product,position ->
            viewModel.toggleHeart(requireContext(), product,position, adapter.productList)
        }
        binding.rvWishlist.adapter = adapter
        binding.rvWishlist.layoutManager = GridLayoutManager(requireContext(),2)

        viewModel.fetchWishlistProducts(requireContext())

        lifecycleScope.launch {
            viewModel.wishlistProducts.collect { products ->
                adapter.updateList(products.toMutableList())
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}