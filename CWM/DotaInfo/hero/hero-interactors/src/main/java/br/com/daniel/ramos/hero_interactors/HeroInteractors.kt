package br.com.daniel.ramos.hero_interactors

import br.com.daniel.ramos.hero_datasource.network.HeroService

/**
 *
 * Kind of wrapper class
 */
data class HeroInteractors(
    val getHeroes: GetHeroes,
    //TODO(Add other hero interactors)
) {
    /**
     * Construimos nossos interactors, dando acesso aos useCases
     */
    companion object Factory {
        fun build(): HeroInteractors {
            val service = HeroService.build()
            return HeroInteractors(
                getHeroes = GetHeroes(service = service)
            )
        }
    }
}