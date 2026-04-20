package com.umc.workbook.week2.ui.wishlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class WishlistViewModel @Inject constructor(
    private val repository: LocalShopRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(WishlistUiState())
    val uiState: StateFlow<WishlistUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getShopProducts().collect { products ->
                _uiState.update { it.copy(wishlisted = products.filter { p -> p.isWishlisted }) }
            }
        }
    }
}

data class WishlistUiState(
    val wishlisted: List<ShopProductData> = emptyList()
)