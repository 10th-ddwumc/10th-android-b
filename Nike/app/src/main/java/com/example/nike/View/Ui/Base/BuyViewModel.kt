package com.example.nike.com.example.nike.View.Ui.Base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nike.R
import com.example.nike.com.example.nike.data.model.BuyItemData
import com.example.nike.com.example.nike.data.repository.BuyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class BuyViewModel @Inject constructor(
    private val repository: BuyRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(BuyUiState())
    val uiState: StateFlow<BuyUiState> = _uiState.asStateFlow()

    private val buyItems = listOf(
        BuyItemData("B_item1", R.drawable.image_1_2, "Nike Air Force 1 '07", "US$115", "Training Ankle Socks (6pairs)\n5colors"),
        BuyItemData("B_item2", R.drawable.image_5, "Nike Elite Crew", "US$16", "Basketball Socks\n7colors"),
        BuyItemData("B_item3", R.drawable.image_2_1, "Nike Everyday Plus Cushioned", "US$10", "Women's Shoes\n5colors"),
        BuyItemData("B_item4", R.drawable.image_3_1, "Jordan Essentials", "US$115", "Men’s Fleece Pullover Hoodie\n2colors"),
        BuyItemData("B_item5", R.drawable.image_4, "Air Jordan XXXVI", "US$185"),
        BuyItemData("B_item6", R.drawable.image_4_1, "양말2", "US$00"),
        BuyItemData("B_item7", R.drawable.image_6, "Jordan Essentials", "US$60")
    )

    init {
        viewModelScope.launch {
            repository.initBuyItemsIfEmpty(buyItems)

            repository.getBuyItems().collect { items ->
                _uiState.update {
                    it.copy(buyItems = items)
                }
            }
        }
    }

    fun toggleWishItem(itemId: String) {
        viewModelScope.launch {
            repository.toggleWishItem(itemId)
        }
    }
}

data class BuyUiState(
    val buyItems: List<BuyItemData> = emptyList()
)