package com.example.alphainternproject.domain.usecase

import com.example.alphainternproject.domain.model.BinModel
import com.example.alphainternproject.domain.repository.BinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import android.util.Log

class GetAllBinUseCase @Inject constructor(
    private val repository: BinRepository
) {
    // Функция-оператор вызывает репозиторий для получения данных
    operator fun invoke(): Flow<List<BinModel>> {
        return repository.getAllBins()
    }
}
