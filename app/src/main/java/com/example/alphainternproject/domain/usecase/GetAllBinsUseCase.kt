package com.example.alphainternproject.domain.usecase

import com.example.alphainternproject.domain.model.BinModel
import com.example.alphainternproject.domain.repository.BinRepository
import kotlinx.coroutines.flow.Flow



class GetAllBinUseCase (
    private val repository: BinRepository
) {

    operator fun invoke(): Flow<List<BinModel>> {
        return repository.getAllBins()
    }

}