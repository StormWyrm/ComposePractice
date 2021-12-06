package com.github.stormwyrm.weather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.stormwyrm.weather.common.State
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {
    val stateLiveData = MutableLiveData<State>().apply {
        value = State.Loading
    }

    fun launch(block : suspend CoroutineScope.() -> Unit){
        viewModelScope.launch {
            kotlin.runCatching {
                block()
            }.onSuccess {
                stateLiveData.value = State.Success
            }.onFailure {
                stateLiveData.value = State.Error(it.message ?: "")
            }
        }
    }
}