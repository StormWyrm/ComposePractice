package com.github.stormwyrm.weather.common

sealed class State {

    object Loading : State()

    object Success : State()

    class Error(val msg: String = "") : State()

    fun isLoading() = this is Loading

    fun isError() = this is Error
}

