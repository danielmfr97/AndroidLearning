package com.example.cleanarchitecturelearn.business.domain.state

// Eventos que ocorrem em uma implementacao MVI
interface StateEvent {

    fun errorInfo(): String

    fun eventName(): String

    fun shouldDisplayProgressBar(): Boolean
}