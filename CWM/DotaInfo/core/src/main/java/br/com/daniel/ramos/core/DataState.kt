package br.com.daniel.ramos.core

sealed class DataState<T> {
    /**
     * Vai retornar para nos caso algo dÊ errado ou for algo positivo
     */
    data class Response<T> (
        val uiComponent: UIComponent
    ): DataState<T>()


    data class Data<T>(
        val data:T? = null
    ):DataState<T>()

    data class Loading<T>(
        val progressBarState: ProgressBarState = ProgressBarState.Idle
    ): DataState<T>()
}