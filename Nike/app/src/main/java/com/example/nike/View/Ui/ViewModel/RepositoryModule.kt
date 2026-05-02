package com.example.nike.View.Ui.ViewModel

import com.example.nike.com.example.nike.data.repository.BuyRepository
import com.example.nike.data.repository.BuyRepositoryImpl
import com.example.nike.data.repository.HomeRepository
import com.example.nike.data.repository.HomeRepositoryImpl
import com.example.nike.data.repository.ProfileRepository
import com.example.nike.data.repository.ProfileRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindBuyRepository(
        impl: BuyRepositoryImpl
    ): BuyRepository

    @Binds
    @Singleton
    abstract fun bindHomeRepository(
        impl: HomeRepositoryImpl
    ): HomeRepository

    @Binds
    @Singleton
    abstract fun bindProfileRepository(
        impl: ProfileRepositoryImpl
    ): ProfileRepository
}