package com.example.alphainternproject.presentation.state

import com.example.alphainternproject.domain.model.BinModel


data class BinState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val binHistory: List<BinModel> = emptyList(),
    val binInfo: BinModel? = null

)