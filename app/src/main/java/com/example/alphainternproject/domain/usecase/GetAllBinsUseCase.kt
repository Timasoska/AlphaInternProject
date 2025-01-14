package com.example.alphainternproject.domain.usecase

import com.example.alphainternproject.domain.model.BinModel
import com.example.alphainternproject.domain.repository.BinRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetAllBinUseCase @Inject constructor(
    private val repository: BinRepository
) {

    operator fun invoke(): Flow<List<BinModel>> {
        return repository.getAllBins()
    }

}