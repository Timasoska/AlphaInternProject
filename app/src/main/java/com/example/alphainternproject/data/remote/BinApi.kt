package com.example.alphainternproject.data.remote

import com.example.alphainternproject.domain.model.BinModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BinApi {

    @GET("/{bin}")
    suspend fun getBinInfo(@Path("bin") bin: String): Response<BinModel>

}