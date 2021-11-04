package br.com.daniel.ramos.hero_interactors

import br.com.daniel.ramos.hero_datasource.cache.HeroCache
import br.com.daniel.ramos.hero_datasource.network.HeroService
import com.squareup.sqldelight.db.SqlDriver

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
        fun build(sqlDriver: SqlDriver): HeroInteractors {
            val service = HeroService.build()
            val cache = HeroCache.build(sqlDriver)
            return HeroInteractors(
                getHeroes = GetHeroes(service = service, cache = cache)
            )
        }
        val schema: SqlDriver.Schema = HeroCache.schema

        val dbName = HeroCache.dbName
    }



}