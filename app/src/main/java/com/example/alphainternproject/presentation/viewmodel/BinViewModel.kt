package com.example.alphainternproject.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alphainternproject.domain.usecase.GetAllBinUseCase
import com.example.alphainternproject.domain.usecase.getBinInfoUseCase
import com.example.alphainternproject.domain.usecase.InsertBinUseCase
import com.example.alphainternproject.mappers.toEntity
import com.example.alphainternproject.presentation.event.BinEvent
import com.example.alphainternproject.presentation.state.BinState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
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

    init {
        loadHistory()
    }

    private fun loadHistory(){
        viewModelScope.launch {
            getAllBinUseCase()
                .catch { e ->
                    _state.value = _state.value.copy(error = "Ошибка загрузки истории: ${e.message}")
                }
                .collect { bins ->
                    Log.d("BinViewModel", "История загрузки: $bins")
                    _state.value = _state.value.copy(binHistory = bins)
                }
        }
    }

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
                                isLoading = false,
                                binInfo = binInfo,
                                error = null
                            )
                            Log.d("BinViewModel", "Полученная информация о BIN: $binInfo")
                            binInfo?.let {
                                Log.d("BinViewModel", "Сохранение BIN в историю")
                                insertBinUseCase(it)
                                loadHistory()
                            }
                        } else {
                            _state.value = _state.value.copy(
                                isLoading = false,
                                error = "Ошибка получения данных: ${response.message()}"
                            )
                            Log.d("BinViewModel", "Ошибка получения данных: ${response.message()}")
                        }
                    } catch (e: Exception) {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            error = "Ошибка получения данных: ${e.message}"
                        )
                        Log.e("BinViewModel", "Ошибка получения данных", e)
                    }
                }
            }

            is BinEvent.LoadBinHistory -> {
                loadHistory()
            }
        }
    }
}

