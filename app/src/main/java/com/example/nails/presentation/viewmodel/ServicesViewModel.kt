package com.example.nails.presentation.viewmodel

import android.content.Context
import com.example.nails.data.network.NetworkService
import com.example.nails.presentation.ScreenState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi

class ServicesViewModel (
    private val context: Context,
    private val coroutineScope: CoroutineScope
) {
    private val _screenState = MutableStateFlow<ScreenState> (ScreenState.Loading)
    val screenState : StateFlow<ScreenState> = _screenState
    private var job: Job? = null

    @ExperimentalSerializationApi
    fun loadData() {
        job?.cancel()
        job = coroutineScope.launch {
            try {
                _screenState.emit(ScreenState.Loading)
                val services = NetworkService.loadServices()
                _screenState.emit(ScreenState.DataLoaded(services))
            } catch ( ex: Throwable) {
                _screenState.emit(ScreenState.Error("Внимание, ошибка!!!"))
            }
        }
    }
}
