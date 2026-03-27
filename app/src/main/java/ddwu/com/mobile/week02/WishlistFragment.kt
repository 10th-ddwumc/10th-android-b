package ddwu.com.mobile.week02

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ddwu.com.mobile.week02.databinding.FragmentWishlistBinding

class WishlistFragment : Fragment() {

    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWishlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productList = mutableListOf(
            ProductData("Air Jordan 1 Mid", "US$125",R.drawable.everyday_cushioned),
            ProductData("Nike Everyday Plus Cushioned", "US$10", R.drawable.airforce_women)
        )

        val adapter = ProductAdapter(productList)
        binding.rvWishlist.adapter = adapter
        binding.rvWishlist.layoutManager = GridLayoutManager(requireContext(),2)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}