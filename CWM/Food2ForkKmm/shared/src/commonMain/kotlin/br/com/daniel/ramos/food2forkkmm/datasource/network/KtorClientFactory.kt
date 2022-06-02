package br.com.daniel.ramos.food2forkkmm.datasource.network

import io.ktor.client.*

expect class KtorClientFactory() {
    fun build(): HttpClient
}