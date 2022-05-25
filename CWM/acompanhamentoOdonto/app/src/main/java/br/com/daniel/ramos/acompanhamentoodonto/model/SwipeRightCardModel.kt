package br.com.daniel.ramos.acompanhamentoodonto.model

import androidx.annotation.ColorInt

data class SwipeRightCardModel(
    @ColorInt val backgroundColor: Int,
    val pergunta: String,
    val resposta: Boolean,
    val explicacao: String,
)