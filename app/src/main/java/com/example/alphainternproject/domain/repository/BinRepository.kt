package com.example.alphainternproject.domain.repository

import com.example.alphainternproject.data.local.BinDao
import com.example.alphainternproject.data.local.BinEntity
import com.example.alphainternproject.domain.model.BinModel
import kotlinx.coroutines.flow.Flow

interface BinRepository {
    suspend fun insertBin(bin: BinModel)

    fun getAllBins(): Flow<List<BinModel>>

}