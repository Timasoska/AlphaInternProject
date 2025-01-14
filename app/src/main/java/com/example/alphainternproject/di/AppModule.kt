package com.example.alphainternproject.di

import android.content.Context
import androidx.room.Room
import com.example.alphainternproject.data.local.AppDataBase
import com.example.alphainternproject.data.local.BinDao
import com.example.alphainternproject.data.remote.BinApi
import com.example.alphainternproject.data.repository.BinRepositoryImpl
import com.example.alphainternproject.domain.repository.BinRepository
import com.example.alphainternproject.domain.usecase.GetAllBinUseCase
import com.example.alphainternproject.domain.usecase.getBinInfoUseCase
import com.example.alphainternproject.domain.usecase.InsertBinUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppContext(@ApplicationContext context: Context): Context{
        return context
    }

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
        return dataBase.binDao() // Получение DAO для базы данных
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder() // Создание экземпляра Retrofit для выполнения сетевых запросов
            .baseUrl("https://lookup.binlist.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideBinApi(retrofit: Retrofit) : BinApi{
        return retrofit.create(BinApi::class.java) // Создание API сервиса для выполнения запросов к Binlist API
    }

    @Provides
    @Singleton
    fun provideBinRepository(api: BinApi, binDao: BinDao): BinRepository{
        return BinRepositoryImpl(binDao, api) // Предоставление экземпляра репозитория
    }

    @Provides
    @Singleton
    fun provideGetBinInfoUseCase(repository: BinRepository): getBinInfoUseCase{
        return getBinInfoUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideInsertBinUseCase(repository: BinRepository): InsertBinUseCase{
        return InsertBinUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetAllBinUseCase(repository: BinRepository): GetAllBinUseCase{
        return GetAllBinUseCase(repository)
    }

}