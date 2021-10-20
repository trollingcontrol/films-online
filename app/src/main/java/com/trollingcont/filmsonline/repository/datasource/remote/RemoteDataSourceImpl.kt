package com.trollingcont.filmsonline.repository.datasource.remote

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.trollingcont.filmsonline.model.Film
import com.trollingcont.filmsonline.model.FilmList
import com.trollingcont.filmsonline.repository.network.FilmNetworkService
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import java.lang.IllegalStateException
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val filmsNetworkService: FilmNetworkService
) : RemoteDataSource {

    override fun getFilms(
        onSuccess: (List<Film>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        filmsNetworkService.filmService()
            .getFilmsList()
            .enqueue(
                object : retrofit2.Callback<FilmList> {
                    override fun onResponse(
                        call: Call<FilmList>,
                        response: Response<FilmList>
                    ) {
                        val responseBody = response.body()

                        if (response.isSuccessful && responseBody != null) {
                            onSuccess(responseBody.films)
                        } else {
                            onFailure(IllegalStateException("Response is not correct"))
                        }
                    }

                    override fun onFailure(call: Call<FilmList>, t: Throwable) {
                        onFailure(t)
                    }
                }
            )
    }

    override fun getImageByUrl(
        imageUrl: String,
        onSuccess: (Bitmap) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        filmsNetworkService.filmService()
            .downloadImage(imageUrl)
            .enqueue(
                object : retrofit2.Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        val responseBody = response.body()

                        if (response.isSuccessful && responseBody != null) {
                            val bitmap = BitmapFactory.decodeByteArray(
                                responseBody.bytes(),
                                0,
                                responseBody.contentLength().toInt()
                            )

                            onSuccess(bitmap)
                        } else {
                            onFailure(IllegalStateException("Response is not correct"))
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        onFailure(t)
                    }

                }
            )
    }
}