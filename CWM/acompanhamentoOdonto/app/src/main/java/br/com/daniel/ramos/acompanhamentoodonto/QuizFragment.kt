package br.com.daniel.ramos.acompanhamentoodonto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.daniel.ramos.acompanhamentoodonto.model.data.PerguntaDTO
import br.com.daniel.ramos.acompanhamentoodonto.databinding.FragmentQuizBinding
import br.com.daniel.ramos.acompanhamentoodonto.presenter.dialogs.DialogAcertoErro
import br.com.daniel.ramos.acompanhamentoodonto.presenter.listeners.OnNextQuestiob
import br.com.daniel.ramos.acompanhamentoodonto.utils.JsonUtils.getJsonDataFromAsset
import com.google.gson.GsonBuilder

class QuizFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!

    // TODO: Criar estratégia de cache para caso sair do Quiz, voltar para a pergunta que estava

    private var perguntasDTO: List<PerguntaDTO> = listOf()
    private var currentPosition = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        popularQuiz()
        iniciarViews()
        nextQuestion(currentPosition)
    }

    private fun popularQuiz() {
        val jsonFile = getJsonDataFromAsset(requireContext(), "perguntas.json")
        val gson = GsonBuilder().create()
        perguntasDTO = gson.fromJson(jsonFile,Array<PerguntaDTO>::class.java).toList()
    }

    private fun iniciarViews() {
        binding.btnVerdadeiro.setOnClickListener(this)
        binding.btnFalso.setOnClickListener(this)
    }

    private fun nextQuestion(posicao: Int) {
        lateinit var pergunta: PerguntaDTO
        if (posicao == perguntasDTO.size) {
            currentPosition = 0
            pergunta = perguntasDTO[currentPosition]
        } else {
            pergunta = perguntasDTO[posicao]
        }
        binding.textoPergunta.text = pergunta.pergunta
    }

    private val onNextQuestion = object : OnNextQuestiob {
        override fun executar() {
            currentPosition += 1
            nextQuestion(currentPosition)
        }
    }

    private fun validarResposta(resposta: Boolean) {
        val respostaCorreta = (perguntasDTO[currentPosition].resposta == resposta)
        DialogAcertoErro(respostaCorreta, "Explicacao", requireContext(), onNextQuestion).exibir()
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnVerdadeiro -> {
                validarResposta(true)
            }
            binding.btnFalso -> {
                validarResposta(false)
            }
        }
    }
}