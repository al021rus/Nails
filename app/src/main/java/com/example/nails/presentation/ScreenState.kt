package com.example.nails.presentation


sealed class ScreenState {
        data class DataLoaded(val services: List<com.example.nails.domain.model.Service>) : ScreenState()
        object Loading : ScreenState()
        data class Error(val error: String) : ScreenState()
}
