package br.com.daniel.ramos.acompanhamentoodonto.presenter.dialogs

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import br.com.daniel.ramos.acompanhamentoodonto.MainActivity
import br.com.daniel.ramos.acompanhamentoodonto.presenter.listeners.OnNextQuestiob
import br.com.daniel.ramos.acompanhamentoodonto.view.dialogs.DialogAcertoErroView
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class DialogAcertoErro(respostaCorreta: Boolean, explicacao: String, context: Context, onNextQuestion: OnNextQuestiob) {
    val view = DialogAcertoErroView(respostaCorreta, explicacao)

    private val onButtonClick = DialogInterface.OnClickListener { dialog, which ->
        if (which == DialogInterface.BUTTON_NEUTRAL) {
            onNextQuestion.executar()
            dialog.dismiss()
        }
    }

    val dialog: Dialog = MaterialAlertDialogBuilder(MainActivity.instance!!)
        .setTitle("Teste")
        .setView(view.getView())
        .setNeutralButton("NEUTRAL", onButtonClick)
        .setCancelable(false)
        .create()

    fun exibir() {
        dialog.show()
    }
}