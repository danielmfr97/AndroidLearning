package com.devventure.moviesmvvm.data.model.movie

data class Movie(
    val id: Int,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
)