package com.trollingcont.filmsonline.repository.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class FilmNetworkServiceImpl @Inject constructor() : FilmNetworkService {

    private companion object {
        const val BASE_URL = "https://s3-eu-west-1.amazonaws.com"
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(FilmNetworkInterface::class.java)

    override fun filmService(): FilmNetworkInterface = service
}