package br.com.daniel.ramos.learningjetpackcompose.presentation.util

import androidx.compose.material.ScaffoldState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SnackbarController(
    private val scope: CoroutineScope
) {
    private var snackbarJob: Job? = null

    init {
        cancelActiveJob()
    }

    fun getScope() = scope

    fun showSnackbar(scaffoldState: ScaffoldState, message: String, actionLabel: String) {
        if (snackbarJob != null)
            cancelActiveJob()

        snackbarJob = scope.launch {
            scaffoldState.snackbarHostState.showSnackbar(
                message = message,
                actionLabel = actionLabel
            )
            cancelActiveJob()
        }
    }

    //    Cancels a snackbar that already exists and creates a new one, avoiding put snackbar on queue
    private fun cancelActiveJob() {
        snackbarJob?.let { job ->
            job.cancel()
            snackbarJob = Job()
        }
    }
}