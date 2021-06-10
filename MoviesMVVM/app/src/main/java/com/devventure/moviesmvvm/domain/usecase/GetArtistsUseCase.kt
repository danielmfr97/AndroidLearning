package com.devventure.moviesmvvm.domain.usecase

import com.devventure.moviesmvvm.data.model.artist.Artist
import com.devventure.moviesmvvm.domain.repository.ArtistRepository

/**
 * Created by danie on 10/06/2021
 */
class GetArtistsUseCase(private val artistRepository: ArtistRepository) {
    suspend fun execute(): List<Artist>? = artistRepository.getArtists()
}