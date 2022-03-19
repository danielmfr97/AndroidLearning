package br.com.daniel.ramos.learningjetpackcompose.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.daniel.ramos.learningjetpackcompose.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val TAG = "MainActivity "

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}