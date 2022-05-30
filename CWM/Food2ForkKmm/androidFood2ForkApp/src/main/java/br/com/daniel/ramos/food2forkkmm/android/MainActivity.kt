package br.com.daniel.ramos.food2forkkmm.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.daniel.ramos.food2forkkmm.Greeting
import android.widget.TextView
import androidx.activity.compose.setContent
import br.com.daniel.ramos.food2forkkmm.android.presentation.navigation.Navigation

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation()
        }
    }
}
