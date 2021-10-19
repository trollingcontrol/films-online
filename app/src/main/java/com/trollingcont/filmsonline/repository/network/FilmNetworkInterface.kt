package com.trollingcont.filmsonline.repository.network

import com.trollingcont.filmsonline.model.FilmList
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface FilmNetworkInterface {
    @GET("/sequeniatesttask/films.json")
    fun getFilmsList(): Call<FilmList>

    @GET
    fun downloadImage(@Url imageUrl: String): Call<ResponseBody>
}