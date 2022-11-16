package com.suprematic.rawscore.di

import com.suprematic.data.RepositoryImpl
import com.suprematic.domain.Repository
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
    abstract fun provideRepository(
        repository: RepositoryImpl
    ): Repository
}