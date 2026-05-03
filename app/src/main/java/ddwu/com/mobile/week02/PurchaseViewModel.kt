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

@HiltViewModel
class PurchaseViewModel @Inject constructor(
    private val localRepository: LocalRepository
) : ViewModel() {
    private val _purchaseProducts = MutableStateFlow<List<ProductData>>(emptyList())
    val purchaseProducts: StateFlow<List<ProductData>> = _purchaseProducts

    private val _wishlistProducts = MutableStateFlow<List<ProductData>>(emptyList())
    val wishlistProducts: StateFlow<List<ProductData>> = _wishlistProducts

    fun fetchPurchaseProducts(context: Context, initialList: List<ProductData>) {
        viewModelScope.launch {
            localRepository.getPurchaseProducts(context).collect { products->
                if(products.isEmpty()){
                    localRepository.savePurchaseProducts(context,initialList)
                } else {
                    _purchaseProducts.value = products
                }
            }
        }
    }

    fun savePurchaseProducts(context: Context, products:List<ProductData>) {
        viewModelScope.launch {
            localRepository.savePurchaseProducts(context,products)
        }
    }

    fun toggleHeart(context:Context, product: ProductData, position:Int, currentList:MutableList<ProductData>){
        viewModelScope.launch {
            val updatedProduct = product.copy(isLiked = !product.isLiked)
            currentList[position] = updatedProduct
            localRepository.savePurchaseProducts(context,currentList)

            val wishProducts = localRepository.getWishlistProducts(context).collect { wishlist ->
                val newWishlist = wishlist.toMutableList()
                if(updatedProduct.isLiked) {
                    if(!newWishlist.any {it.name == updatedProduct.name}) {
                        newWishlist.add(updatedProduct)
                    }
                } else{
                    newWishlist.removeAll{it.name == updatedProduct.name}
                }
                localRepository.saveWishlistProducts(context,newWishlist)
            }
        }
    }
}