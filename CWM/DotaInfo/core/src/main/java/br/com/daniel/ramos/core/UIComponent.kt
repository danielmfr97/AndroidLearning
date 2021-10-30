package br.com.daniel.ramos.core

sealed class UIComponent{
    data class Dialog(
        val title: String,
        val description: String
    ): UIComponent()

    /**
     * Algo que queiramos fazer Log ou enviar para crashlytics
     */
    data class  None(
        val message: String
    ): UIComponent()
}