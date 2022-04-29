package br.com.daniel.ramos.learningjetpackcompose.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.daniel.ramos.learningjetpackcompose.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}