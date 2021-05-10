package edu.pvdt.dotify.repository

import edu.pvdt.dotify.model.User
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

//https://raw.githubusercontent.com/echeeUW/codesnippets/master/allartists.json

class DataRepository {
    private val artistService = Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ArtistService::class.java)

    suspend fun getArtists() = artistService.getArtists()
}

interface ArtistService{
    @GET("echeeUW/codesnippets/master/allartists.json")
    suspend fun getArtists(): User
}