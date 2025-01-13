package com.example.alphainternproject.domain.usecase

import com.example.alphainternproject.data.local.BinEntity
import com.example.alphainternproject.domain.model.BinModel
import com.example.alphainternproject.domain.repository.BinRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InsertBinUseCase(
    private val repository: BinRepository
){

    suspend operator fun invoke(binModel: BinModel){
        repository.insertBin(binModel)
    }

}