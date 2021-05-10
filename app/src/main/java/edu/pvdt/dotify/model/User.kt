package edu.pvdt.dotify.model

data class User(
    val username: String,
    val firstName: String,
    val lastName: String,
    val hasNose: Boolean,
    val platform: Double,
    val profilePicURL: String,
)