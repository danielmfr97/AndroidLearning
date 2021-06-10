package com.devventure.moviesmvvm.domain.repository

import com.devventure.moviesmvvm.data.model.movie.Movie
import com.devventure.moviesmvvm.data.model.tvshow.TvShow

/**
 * Created by danie on 10/06/2021
 */
interface TvShowRepository {
    suspend fun getTvShows(): List<TvShow>?
    suspend fun updateTvShows(): List<TvShow>?
}