package ddwu.com.mobile.week02

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ddwu.com.mobile.week02.local.LocalRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.first

@HiltViewModel
class WishlistViewModel @Inject constructor(
    private val localRepository: LocalRepository
) : ViewModel() {
    private val _wishlistProducts = MutableStateFlow<List<ProductData>>(emptyList())
    val wishlistProducts: StateFlow<List<ProductData>> = _wishlistProducts

    fun fetchWishlistProducts(context: Context) {
        viewModelScope.launch {
            localRepository.getWishlistProducts(context).collect { products ->
                _wishlistProducts.value = products
            }
        }
    }

    fun toggleHeart(context: Context, product: ProductData, position: Int, currentList: MutableList<ProductData>){
        viewModelScope.launch {
            val updatedProduct = product.copy(isLiked = !product.isLiked)
            currentList[position] = updatedProduct

            val newWishlist = currentList.filter { it.isLiked }.toMutableList()
            localRepository.saveWishlistProducts(context,newWishlist)
            _wishlistProducts.value = newWishlist

            val purchaseProducts = localRepository.getPurchaseProducts(context).first()
            val newPurchaseList = purchaseProducts.toMutableList()
            val index = newPurchaseList.indexOfFirst { it.name == updatedProduct.name }
            if(index != -1) {
                newPurchaseList[index] = updatedProduct
                localRepository.savePurchaseProducts(context,newPurchaseList)
            }

        }
    }
}
