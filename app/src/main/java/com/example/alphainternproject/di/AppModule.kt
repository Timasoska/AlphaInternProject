package com.example.alphainternproject.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.alphainternproject.data.local.AppDataBase
import com.example.alphainternproject.data.local.BinDao
import com.example.alphainternproject.data.remote.BinApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://lookup.binlist.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideBinApi(retrofit: Retrofit) : BinApi{
        return retrofit.create(BinApi::class.java)
    }

}