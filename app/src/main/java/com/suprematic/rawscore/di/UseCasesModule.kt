package com.suprematic.rawscore.di

import com.suprematic.domain.Repository
import com.suprematic.domain.usecases.InitializeGameUseCase
import com.suprematic.domain.usecases.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {
    @Provides
    @Singleton
    fun provideUseCases(
        repository: Repository
    ): UseCases =
        UseCases(
            initializeGameUseCase = InitializeGameUseCase(repository)
        )
}