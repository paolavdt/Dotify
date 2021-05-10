package edu.pvdt.dotify.repository

import edu.pvdt.dotify.model.Songs
import edu.pvdt.dotify.model.User
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

//https://raw.githubusercontent.com/echeeUW/codesnippets/master/musiclibrary.json

class DataRepository {
    private val dotifyService = Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(DotifyService::class.java)

    suspend fun getUser() = dotifyService.getUser()
    suspend fun getSongs() = dotifyService.getSongs()
}

interface DotifyService{
    @GET("echeeUW/codesnippets/master/user_info.json")
    suspend fun getUser(): User

    @GET("echeeUW/codesnippets/master/musiclibrary.json")
    suspend fun getSongs(): Songs
}