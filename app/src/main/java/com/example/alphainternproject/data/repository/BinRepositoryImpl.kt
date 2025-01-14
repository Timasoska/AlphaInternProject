package com.example.alphainternproject.data.repository

import com.example.alphainternproject.data.local.BinDao
import com.example.alphainternproject.data.local.toDomainModel
import com.example.alphainternproject.data.remote.BinApi
import com.example.alphainternproject.domain.model.BinModel
import com.example.alphainternproject.domain.model.toEntity
import com.example.alphainternproject.domain.repository.BinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.Response
import javax.inject.Inject

class BinRepositoryImpl @Inject constructor(
    private val dao: BinDao,
    private val api: BinApi
): BinRepository {
    override suspend fun insertBin(bin: BinModel) {
        dao.insertBin(bin.toEntity())
    }

    override fun getAllBins(): Flow<List<BinModel>> {
        return dao.getAllBin().map { entities ->
            entities.map { it.toDomainModel() }
        }
    }

    override suspend fun getBinInfo(bin: String): Response<BinModel> {
        return api.getBinInfo(bin)
    }
}