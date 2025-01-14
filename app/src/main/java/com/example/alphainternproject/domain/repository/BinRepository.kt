package com.example.alphainternproject.domain.repository

import com.example.alphainternproject.data.local.BinEntity
import com.example.alphainternproject.domain.model.BinModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface BinRepository {
    suspend fun insertBin(bin: BinEntity)

    fun getAllBins(): Flow<List<BinModel>>

    suspend fun getBinInfo(bin: String): Response<BinModel>

}