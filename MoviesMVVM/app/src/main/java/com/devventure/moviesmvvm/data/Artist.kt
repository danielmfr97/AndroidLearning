package com.devventure.moviesmvvm.data

data class Artist(
    val id: Int,
    val known_for: List<KnownFor>,
    val name: String,
    val popularity: Double,
    val profile_path: String
)