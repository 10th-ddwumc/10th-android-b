package ddwu.com.mobile.week02

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ddwu.com.mobile.week02.local.LocalRepository
import ddwu.com.mobile.week02.remote.RemoteRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRemoteRepository(): RemoteRepository{
        return RemoteRepository()
    }

    @Provides
    @Singleton
    fun provideLocalRepository() : LocalRepository {
        return LocalRepository()
    }
}