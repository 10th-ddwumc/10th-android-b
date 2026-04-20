package com.umc.workbook.week2.di

import com.umc.workbook.week2.data.HomeDataStore
import com.umc.workbook.week2.data.ShopDataStore
import com.umc.workbook.week2.domain.repository.LocalHomeRepository
import com.umc.workbook.week2.domain.repository.LocalShopRepository
import com.umc.workbook.week2.domain.repository.RemoteUserRepository
import com.umc.workbook.week2.network.UserRepository
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
    abstract fun bindLocalHomeRepository(impl: HomeDataStore): LocalHomeRepository

    @Binds
    @Singleton
    abstract fun bindLocalShopRepository(impl: ShopDataStore): LocalShopRepository

    @Binds
    @Singleton
    abstract fun bindRemoteUserRepository(impl: UserRepository): RemoteUserRepository
}