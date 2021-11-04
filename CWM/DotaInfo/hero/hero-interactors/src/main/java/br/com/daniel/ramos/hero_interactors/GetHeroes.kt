package br.com.daniel.ramos.hero_interactors

import br.com.daniel.ramos.core.DataState
import br.com.daniel.ramos.core.Logger
import br.com.daniel.ramos.core.ProgressBarState
import br.com.daniel.ramos.core.UIComponent
import br.com.daniel.ramos.hero_datasource.cache.HeroCache
import br.com.daniel.ramos.hero_datasource.network.HeroService
import br.com.daniel.ramos.hero_domain.Hero
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetHeroes(
    private val service: HeroService,
    private val cache: HeroCache,
//    private val logger: Logger,
    //TODO: (add caching)
) {
    fun execute(): Flow<DataState<List<Hero>>> = flow {
        // Catch network exception, in the future we will cache data
        try {
            emit(DataState.Loading(progressBarState = ProgressBarState.Loading))
            val heros: List<Hero> = try {
                service.getHeroStats()
            } catch (e: Exception) {
                e.printStackTrace()
//            logger.log("log to crashlytics if needed")
                emit(
                    DataState.Response<List<Hero>>(
                        uiComponent = UIComponent.Dialog(
                            title = "Network Data Error",
                            description = e.message ?: "Unknown Error"
                        )
                    )
                )
                listOf()
            }

            // Cache the network data
            cache.insert(heros)
            // emit data from cache
            val cachedHeros = cache.selectAll()

            emit(DataState.Data(heros))
        } catch (e: Exception) {
            e.printStackTrace()
//            logger.log("log to crashlytics if needed")
            emit(
                DataState.Response<List<Hero>>(
                    uiComponent = UIComponent.Dialog(
                        title = "Error",
                        description = e.message ?: "Unknown Error"
                    )
                )
            )
        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }
}