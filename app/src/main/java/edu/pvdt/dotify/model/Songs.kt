package edu.pvdt.dotify.model

data class Songs (
    val title: String,
    val numOfSongs: Int,
    val songs: List<Song>
)