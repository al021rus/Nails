package com.example.nails

import android.app.Service


sealed class ScreenState {
        data class DataLoaded(val services: List<com.example.nails.model.Service>) : ScreenState()
        object Loading : ScreenState()
        data class Error(val error: String) : ScreenState()
}
