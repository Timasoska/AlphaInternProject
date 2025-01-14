package com.example.alphainternproject.data.repository

import android.util.Log
import com.example.alphainternproject.data.local.BinDao
import com.example.alphainternproject.data.local.BinEntity
import com.example.alphainternproject.data.remote.BinApi
import com.example.alphainternproject.domain.model.BinModel
import com.example.alphainternproject.domain.repository.BinRepository
import com.example.alphainternproject.mappers.toDomainModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject
import kotlinx.coroutines.flow.map

class BinRepositoryImpl @Inject constructor(
    private val dao: BinDao,
    private val api: BinApi
) : BinRepository {
    override suspend fun insertBin(bin: BinEntity) {
        dao.insertBin(bin)
    }

    override fun getAllBins(): Flow<List<BinModel>> {
        return dao.getAllBin().map { entities ->
            Log.d("BinRepositoryImpl", "Приведение BIN: $entities")
            entities.map { it.toDomainModel() }
        }
    }


    override suspend fun getBinInfo(bin: String): Response<BinModel> {
        return api.getBinInfo(bin)
    }
}

