package com.devventure.moviesmvvm.domain.repository

import com.devventure.moviesmvvm.data.model.movie.Movie

/**
 * Created by danie on 10/06/2021
 */
interface MovieRepository {
    suspend fun getMovies(): List<Movie>?
    suspend fun updateMovies(): List<Movie>?
}