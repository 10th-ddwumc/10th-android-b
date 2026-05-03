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
class HomeViewModel @Inject constructor(
    private val localRepository: LocalRepository
) : ViewModel() {
    private val _homeProducts = MutableStateFlow<List<ProductData>>(emptyList())
    val homeProducts: StateFlow<List<ProductData>> = _homeProducts

    fun fetchHomeProducts(context: Context) {
        viewModelScope.launch {
            localRepository.getHomeProducts(context).collect { products->
                _homeProducts.value = products
            }
        }
    }

    fun saveHomeProducts(context:Context, products: List<ProductData>) {
        viewModelScope.launch{
            localRepository.saveHomeProducts(context,products)
        }
    }
}