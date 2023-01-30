package com.example.contactapplication.di

import com.example.contactapplication.data.repository.AppRepository
import com.example.contactapplication.data.repository.DatabaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideDatabaseRepository(): AppRepository {
        return DatabaseRepository()
    }
}
