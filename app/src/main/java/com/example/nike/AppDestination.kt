package com.example.nike

import kotlinx.serialization.Serializable

sealed interface AppDestination {

    @Serializable
    data object Home : AppDestination

    @Serializable
    data object Buy : AppDestination

    @Serializable
    data object Pocket : AppDestination

    @Serializable
    data object Wish : AppDestination

    @Serializable
    data object Profit : AppDestination
    @Serializable
    data object ReProfit : AppDestination
}