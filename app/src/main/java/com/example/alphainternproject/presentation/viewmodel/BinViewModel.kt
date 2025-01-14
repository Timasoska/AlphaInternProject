package com.example.alphainternproject.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alphainternproject.domain.repository.BinRepository
import com.example.alphainternproject.domain.usecase.GetAllBinUseCase
import com.example.alphainternproject.domain.usecase.InsertBinUseCase
import com.example.alphainternproject.presentation.event.BinEvent
import com.example.alphainternproject.presentation.state.BinState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BinViewModel @Inject constructor(
    private val getAllBinUseCase: GetAllBinUseCase,
    private val insertBinUseCase: InsertBinUseCase
): ViewModel() {

    private val _state = MutableStateFlow(BinState())
    val state: StateFlow<BinState> = _state

    fun onEvent(event: BinEvent){
        when(event){
            is BinEvent.GetBin -> TODO()
            is BinEvent.LoadBinHistory -> {
                viewModelScope.launch {
                    println("LoadBinHistory Event Triggered")
                    getAllBinUseCase().collect{ bins ->
                        println("Bins collected: $bins")
                        _state.value = _state.value.copy(
                            binHistory = bins
                        )
                    }
                }
            }
        }

    }

}