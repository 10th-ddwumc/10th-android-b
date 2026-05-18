package com.umc.workbook.week2.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umc.workbook.week2.R
import com.umc.workbook.week2.data.NewProductData
import com.umc.workbook.week2.domain.repository.LocalHomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: LocalHomeRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadNewProducts()
    }

    private fun loadNewProducts() {
        viewModelScope.launch {
            repository.getNewProducts().collect { products ->
                if (products.isEmpty()) {
                    repository.saveNewProducts(
                        listOf(
                            NewProductData(R.drawable.new_product1, "Air Jordan XXXVI", "US\$185"),
                            NewProductData(R.drawable.new_product2, "Nike Air Force 1 '07", "US\$115")
                        )
                    )
                } else {
                    _uiState.update { it.copy(products = products) }
                }
            }
        }
    }
}

data class HomeUiState(
    val products: List<NewProductData> = emptyList()
)