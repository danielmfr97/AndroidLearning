package com.devventure.moviesmvvm.data.api

import com.devventure.moviesmvvm.data.model.artist.ArtistList
import com.devventure.moviesmvvm.data.model.movie.MovieList
import com.devventure.moviesmvvm.data.model.tvshow.TvShowList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by danie on 10/06/2021
 */
interface TMDBService {
    // Dado que vamos usar coroutines para processamento em background, declaramos suspending
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): Response<MovieList>

    @GET("tv/popular")
    suspend fun getPopularTvShows(@Query("api_key") apiKey: String): Response<TvShowList>

    @GET("person/popular")
    suspend fun getPopularArtist(@Query("api_key") apiKey: String): Response<ArtistList>
}