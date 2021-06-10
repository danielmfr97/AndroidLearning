package com.devventure.moviesmvvm.data.model.tvshow

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "popular_tvShows")
data class TvShow(
    @PrimaryKey
    val id: Int,
    val first_air_date: String,
    val name: String,
    val overview: String,
    val poster_path: String,
)