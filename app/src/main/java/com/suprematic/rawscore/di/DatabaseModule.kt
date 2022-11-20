package com.suprematic.rawscore.di

import android.app.Application
import androidx.room.Room
import com.suprematic.data.converters.GameTypeConverter
import com.suprematic.data.local.database.RsDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideRsDataBase(
        app: Application,
        typeConverter: GameTypeConverter
    ): RsDataBase {
        return Room.databaseBuilder(
            app,
            RsDataBase::class.java,
            RsDataBase.DATABASE_NAME
        ).addTypeConverter(typeConverter)
            .fallbackToDestructiveMigration()
            .build()
    }
}