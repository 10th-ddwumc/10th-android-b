package com.example.nike.View.Ui.Base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<STATE : UiState>(
    initialPageState: STATE,
) : ViewModel() {

    private val _uiState = MutableStateFlow(initialPageState)

    val uiState: StateFlow<STATE>
        get() = _uiState.asStateFlow()
}