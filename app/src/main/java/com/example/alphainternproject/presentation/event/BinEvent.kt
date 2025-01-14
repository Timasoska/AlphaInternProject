package com.example.alphainternproject.presentation.event

sealed class BinEvent {
    data class GetBin(val binNumber: String): BinEvent()
    object LoadBinHistory: BinEvent()
}