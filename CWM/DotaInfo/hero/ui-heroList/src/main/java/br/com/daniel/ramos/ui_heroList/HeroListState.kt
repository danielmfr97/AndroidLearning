package br.com.daniel.ramos.ui_heroList

import br.com.daniel.ramos.core.ProgressBarState
import br.com.daniel.ramos.hero_domain.Hero

data class HeroListState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val heros: List<Hero> = listOf(),
)
