package com.example.background_erase.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    fun <T> executeTask(
        action: suspend () -> T,
        onError: (Throwable) -> Unit = {},
        onSuccess: (T) -> Unit = {}
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = action.invoke()
                onSuccess.invoke(result)
            } catch (e: Throwable) {
                Log.d("ViewModel ExecuteTask: ", "${this@BaseViewModel.javaClass.simpleName} - ${e::class.java.simpleName} - ${e.message}")
                onError.invoke(e)
            }
        }
    }
}