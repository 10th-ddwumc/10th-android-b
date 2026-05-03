package ddwu.com.mobile.week02

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import ddwu.com.mobile.week02.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productList = mutableListOf(
            ProductData("Air Jordan XXXVI", "US$185", R.drawable.air_jordan),
            ProductData("Nike Air Force 1'07", "US$115", R.drawable.air_force)
        )

        viewModel.saveHomeProducts(requireContext(),productList)
        viewModel.fetchHomeProducts(requireContext())

        lifecycleScope.launch {
            viewModel.homeProducts.collect { products ->
                if(products.isNotEmpty()) {
                    val adapter = ProductAdapter(products.toMutableList(), showHeart = false)
                    binding.rvHome.adapter = adapter
                    binding.rvHome.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}