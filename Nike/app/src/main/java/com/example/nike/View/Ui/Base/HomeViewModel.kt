package com.example.nike.View.Ui.Base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nike.R
import com.example.nike.com.example.nike.data.model.HomeItemData
import com.example.nike.data.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

//BaseViewModel
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    val homeItems = repository.getHomeItems()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    init {
        initHomeItemsIfEmpty()
    }

    private fun initHomeItemsIfEmpty() {
        viewModelScope.launch {
            repository.initHomeItemsIfEmpty(
                listOf(
                    HomeItemData("H_item1", R.drawable.image_4, "Air Jordan XXXVI", "US$185"),
                    HomeItemData("H_item2", R.drawable.image_2_1, "Nike Everyday Plus Cushioned", "US$10")
                )
            )
        }
    }
}