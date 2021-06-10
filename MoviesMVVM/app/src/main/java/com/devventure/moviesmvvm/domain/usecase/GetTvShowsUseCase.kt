package com.devventure.moviesmvvm.domain.usecase

import com.devventure.moviesmvvm.data.model.tvshow.TvShow
import com.devventure.moviesmvvm.domain.repository.TvShowRepository

/**
 * Created by danie on 10/06/2021
 */
class GetTvShowsUseCase(private val tvShowRepository: TvShowRepository) {
    suspend fun execute(): List<TvShow>? = tvShowRepository.getTvShows()
}