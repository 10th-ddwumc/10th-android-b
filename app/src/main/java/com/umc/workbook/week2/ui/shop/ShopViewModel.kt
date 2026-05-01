package com.umc.workbook.week2.ui.shop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umc.workbook.week2.R
import com.umc.workbook.week2.data.ShopProductData
import com.umc.workbook.week2.domain.repository.LocalShopRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(
    private val repository: LocalShopRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ShopUiState())
    val uiState: StateFlow<ShopUiState> = _uiState.asStateFlow()

    init {
        loadShopProducts()
    }

    private fun loadShopProducts() {
        viewModelScope.launch {
            repository.getShopProducts().collect { products ->
                if (products.isEmpty()) {
                    repository.saveShopProducts(
                        listOf(
                            ShopProductData(R.drawable.product1, "Nike Everyday Plus Cushioned", "Training Ankle Socks (6 Pairs)", "5 Colours", "US\$10"),
                            ShopProductData(R.drawable.product3, "Nike Elite Crew", "Basketball Socks", "7 Colours", "US\$16"),
                            ShopProductData(R.drawable.product2, "Nike Air Force 1 '07", "Women's Shoes", "5 Colours", "US\$115", isBestSeller = true),
                            ShopProductData(R.drawable.product2, "Jordan Air Force 1 Essentials", "Men's Shoes", "2 Colours", "US\$115", isBestSeller = true),
                        )
                    )
                } else {
                    _uiState.update { it.copy(products = products) }
                }
            }
        }
    }

    fun toggleWish(position: Int) {
        val current = _uiState.value.products.toMutableList()
        if (position !in current.indices) return
        current[position] = current[position].copy(isWishlisted = !current[position].isWishlisted)
        _uiState.update { it.copy(products = current) }
        viewModelScope.launch {
            repository.saveShopProducts(current)
        }
    }
}

data class ShopUiState(
    val products: List<ShopProductData> = emptyList()
)