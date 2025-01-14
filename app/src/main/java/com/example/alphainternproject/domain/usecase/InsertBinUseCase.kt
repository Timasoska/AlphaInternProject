package com.example.alphainternproject.domain.usecase

import android.util.Log
import com.example.alphainternproject.domain.model.BinModel
import com.example.alphainternproject.domain.repository.BinRepository
import com.example.alphainternproject.mappers.toEntity
import javax.inject.Inject


class InsertBinUseCase @Inject constructor(
    private val repository: BinRepository
) {
    suspend operator fun invoke(binModel: BinModel){
        Log.d("InsertBinUseCase", "Сохранение BIN: $binModel")
        repository.insertBin(binModel.toEntity())
    }
}
