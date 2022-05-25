package br.com.daniel.ramos.acompanhamentoodonto

import android.app.Application
import android.content.Context
import android.graphics.Color
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.daniel.ramos.acompanhamentoodonto.model.SwipeRightModel
import br.com.daniel.ramos.acompanhamentoodonto.model.data.PerguntaDTO
import br.com.daniel.ramos.acompanhamentoodonto.presenter.dialogs.DialogAcertoErro
import br.com.daniel.ramos.acompanhamentoodonto.presenter.listeners.OnNextQuestiob
import br.com.daniel.ramos.acompanhamentoodonto.utils.JsonUtils
import com.google.gson.GsonBuilder

class MotionViewModel(application: Application) : AndroidViewModel(application) {

    private var perguntasDTO: List<PerguntaDTO> = listOf()
    private var currentPosition = 0
     var botCard: PerguntaDTO? = null
     var topCard: PerguntaDTO? = null

    private val stream = MutableLiveData<SwipeRightModel>()

    val modelStream: LiveData<SwipeRightModel>
        get() = stream

    init {
        popularQuiz()
        updateStream()
    }

    fun swipe(resposta: Boolean) {
        validarResposta(resposta)
    }

    private fun validarResposta(resposta: Boolean) {
        val respostaCorreta = (perguntasDTO[currentPosition].resposta == resposta)
        DialogAcertoErro(respostaCorreta, "Explicacao", getApplication<Application>().baseContext, onNextQuestion).exibir()
    }

    private val onNextQuestion = object : OnNextQuestiob {
        override fun executar() {
            currentPosition += 1
            updateStream()
        }
    }

    fun popularQuiz() {
        val jsonFile = JsonUtils.getJsonDataFromAsset(getApplication<Application>().applicationContext, "perguntas.json")
        val gson = GsonBuilder().create()
        perguntasDTO = gson.fromJson(jsonFile, Array<PerguntaDTO>::class.java).toList()
    }

    private fun updateStream() {
        // TODO: GAMBIARRA
        when (currentPosition) {
            perguntasDTO.size -> {
                resetCards()
            }
            perguntasDTO.size - 1 -> {
                topCard = perguntasDTO[perguntasDTO.size - 1]
                botCard = perguntasDTO[0]
            }
            else -> {
                topCard = perguntasDTO[currentPosition]
                botCard = perguntasDTO[currentPosition + 1]
            }
        }
        stream.value = SwipeRightModel(
            top = topCard!!,
            bottom = botCard!!
        )
    }

    private fun resetCards() {
        currentPosition = 0
        topCard = perguntasDTO[currentPosition]
        botCard = perguntasDTO[currentPosition + 1]
    }

}
