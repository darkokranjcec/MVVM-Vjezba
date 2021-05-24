package hr.algebra.mvvm.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ExecutorsModule {

    @DatabaseIOExecutor
    @Singleton
    @Provides
    fun provideDatabaseExecutor(): Executor {
        return Executors.newSingleThreadExecutor()
    }
}