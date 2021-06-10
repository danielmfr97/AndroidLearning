package com.devventure.moviesmvvm.data.db

import androidx.room.Database
import com.devventure.moviesmvvm.data.db.ArtistDao
import com.devventure.moviesmvvm.data.db.MovieDao
import com.devventure.moviesmvvm.data.model.artist.Artist
import com.devventure.moviesmvvm.data.model.movie.Movie
import com.devventure.moviesmvvm.data.model.tvshow.TvShow

/**
 * Created by danie on 10/06/2021
 */
@Database(entities = [Movie::class, TvShow::class, Artist::class],
version = 1,
exportSchema = false)
abstract class TMDBDatabase {
    abstract fun movieDao(): MovieDao
    abstract fun tvDao(): MovieDao
    abstract fun artistDao(): ArtistDao
}