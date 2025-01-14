package com.example.alphainternproject.presentation.state

import com.example.alphainternproject.domain.model.BinModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import java.lang.Thread.State

data class BinState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val binHistory: List<BinModel> = emptyList(),
    val binInfo: BinModel? = null

)