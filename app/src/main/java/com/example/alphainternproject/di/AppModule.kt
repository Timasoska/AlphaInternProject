package com.example.alphainternproject.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.alphainternproject.data.local.AppDataBase
import com.example.alphainternproject.data.local.BinDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesBinDataBase(context: Context): AppDataBase{
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "bin_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideBinDao(dataBase: AppDataBase) : BinDao {
        return dataBase.binDao()
    }

}