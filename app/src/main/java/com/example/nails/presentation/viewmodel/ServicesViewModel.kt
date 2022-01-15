package com.example.nails.presentation.viewmodel

import android.content.Context
import com.example.nails.data.database.DatabaseProvider
import com.example.nails.data.network.NetworkService
import com.example.nails.presentation.ScreenState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import java.io.IOException

class ServicesViewModel (
    private val context: Context,
    private val coroutineScope: CoroutineScope
) {
    private val serviceDao = DatabaseProvider.provideDatabase(context).ServiceDAO()
    private val _screenState = MutableStateFlow<ScreenState> (ScreenState.Loading)
    val screenState : StateFlow<ScreenState> = _screenState
    private var job: Job? = null

    @ExperimentalSerializationApi
    fun loadData() {
        job?.cancel()
        job = coroutineScope.launch {
            try{
                _screenState.value = ScreenState.Loading
                val services = try{
                    NetworkService.loadServices().also{
                        serviceDao.insertAll(it)
                    }
                } catch (ex: IOException){
                    serviceDao.getAll()
                }
                _screenState.value = ScreenState.DataLoaded(services)
            } catch (ex: Throwable){
                _screenState.value = ScreenState.Error("Ошибка!")
            }
        }
    }
}
