package com.example.nails

import android.view.View
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

fun View.onClickFlow(): SharedFlow<Unit> {
    val sharedFlow = MutableSharedFlow<Unit>(
        replay = 0,
        extraBufferCapacity = 1
    )
    setOnClickListener{
        sharedFlow.tryEmit(Unit)
    }
    return sharedFlow.asSharedFlow()
}

fun SwipeRefreshLayout.onRefreshFlow() : SharedFlow<Unit>{
    val sharedFlow = MutableSharedFlow<Unit>(
        replay = 0,
        extraBufferCapacity = 1
    )
    setOnRefreshListener {
        sharedFlow.tryEmit(Unit)
    }
    return sharedFlow.asSharedFlow()
}