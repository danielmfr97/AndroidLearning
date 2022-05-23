package br.com.daniel.ramos.acompanhamentoodonto.view.dialogs

import android.view.View
import br.com.daniel.ramos.acompanhamentoodonto.MainActivity
import br.com.daniel.ramos.acompanhamentoodonto.R
import br.com.daniel.ramos.acompanhamentoodonto.databinding.DialogAcertoErroBinding

class DialogAcertoErroView(respostaCorreta: Boolean, explicacao: String) {

    private var binding = DialogAcertoErroBinding.inflate(
        MainActivity.instance!!.layoutInflater
    )
    val view = binding.root

    init {
        binding.tvExplicacao.text = explicacao
        if (respostaCorreta)
            binding.ivAcertoErro.setBackgroundResource(R.drawable.ic_baseline_check_24)
        else
            binding.ivAcertoErro.setBackgroundResource(R.drawable.ic_outline_cancel_24)
    }

    fun getView(): View {
        return view
    }

}