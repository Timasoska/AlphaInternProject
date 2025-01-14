package com.example.alphainternproject.domain.usecase

import com.example.alphainternproject.domain.model.BinModel
import com.example.alphainternproject.domain.repository.BinRepository
import retrofit2.Response
import javax.inject.Inject

class getBinInfoUseCase @Inject constructor(
    private val repository: BinRepository
){

    suspend operator fun invoke(bin: String) : Response<BinModel> {
        return repository.getBinInfo(bin)
    }

}