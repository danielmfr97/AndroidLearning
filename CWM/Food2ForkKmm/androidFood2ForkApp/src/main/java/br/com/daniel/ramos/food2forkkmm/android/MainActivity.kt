package br.com.daniel.ramos.food2forkkmm.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.daniel.ramos.food2forkkmm.Greeting
import androidx.activity.compose.setContent
import br.com.daniel.ramos.food2forkkmm.android.presentation.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint

fun greet(): String {
    return Greeting().greeting()
}

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation()
        }
    }
}
