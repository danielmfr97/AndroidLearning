package br.com.daniel.ramos.ui_heroList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import br.com.daniel.ramos.core.ProgressBarState
import coil.ImageLoader

@Composable
fun HeroList(state: HeroListState, imageLoader: ImageLoader) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn{
            items(state.heros) { hero ->
                HeroListItem(
                    hero = hero,
                    onSelectHero = {},
                    imageLoader = imageLoader
                )
            }
        }
        if (state.progressBarState is ProgressBarState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }

}