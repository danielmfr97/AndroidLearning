package com.devventure.moviesmvvm.domain.usecase

import com.devventure.moviesmvvm.data.model.movie.Movie
import com.devventure.moviesmvvm.domain.repository.MovieRepository

/**
 * Created by danie on 10/06/2021
 */
class UpdateMoviesUseCase(private val movieRepository: MovieRepository){
    suspend fun execute(): List<Movie>? = movieRepository.updateMovies()
}