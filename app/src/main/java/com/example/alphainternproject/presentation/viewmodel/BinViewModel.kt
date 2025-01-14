package com.example.alphainternproject.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alphainternproject.domain.usecase.GetAllBinUseCase
import com.example.alphainternproject.domain.usecase.getBinInfoUseCase
import com.example.alphainternproject.domain.usecase.InsertBinUseCase
import com.example.alphainternproject.presentation.event.BinEvent
import com.example.alphainternproject.presentation.state.BinState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BinViewModel @Inject constructor(
    private val getAllBinUseCase: GetAllBinUseCase,
    private val insertBinUseCase: InsertBinUseCase,
    private val getBinInfoUseCase: getBinInfoUseCase
): ViewModel() {

    private val _state = MutableStateFlow(BinState())
    val state: StateFlow<BinState> = _state

    fun onEvent(event: BinEvent){
        when(event){
            is BinEvent.GetBin -> {
                viewModelScope.launch {
                    _state.value = _state.value.copy(isLoading = true)
                    Log.d("BinViewModel", "Loading started")
                    try{
                        val response = getBinInfoUseCase(event.binNumber)
                        Log.d("BinViewModel", "Response received: $response")
                        if (response.isSuccessful){
                            val binInfo = response.body()
                            _state.value = _state.value.copy(
                                isLoading = true,
                                binInfo = binInfo,
                                error = null
                            )
                        } else {
                            _state.value = _state.value.copy(
                                isLoading = false,
                                error = "Ошибка получения данных: ${response.message()}"
                            )
                        }
                    } catch (e: Exception) {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            error = "Ошибка получения данных ${e.message}"
                        )
                    }
                }
            }

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