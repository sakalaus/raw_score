package com.suprematic.rawscore.di

import com.suprematic.data.local.database.RepositoryImpl
import com.suprematic.data.local.datastore.SettingsRepositoryImpl
import com.suprematic.domain.Repository
import com.suprematic.domain.SettingsRepository
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

    @Binds
    @Singleton
    abstract fun provideSettingsRepository(
        repository: SettingsRepositoryImpl
    ): SettingsRepository

}