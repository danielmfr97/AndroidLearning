package br.com.daniel.ramos.hero_datasource.network

import br.com.daniel.ramos.hero_domain.Hero
import io.ktor.client.*
import io.ktor.client.request.*
import kotlin.text.get

class HeroServiceImpl(
    private val httpClient: HttpClient /***/
): HeroService {
    /**
     * Recuperamos uma lista de HeroDto da rede
     * Usamos o endpoint passado na URL
     * Mapeamos cada HeroDto para um HeroObject
     */
    override suspend fun getHeroStats(): List<Hero> {
    return httpClient.get<List<HeroDto>> {
        url(EndPoints.HERO_STATS)
    }.map { it.toHero() }
    }
}