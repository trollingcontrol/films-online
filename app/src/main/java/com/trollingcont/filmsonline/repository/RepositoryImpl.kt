package com.trollingcont.filmsonline.repository

import android.graphics.Bitmap
import com.trollingcont.filmsonline.model.Film
import com.trollingcont.filmsonline.repository.datasource.local.LocalDataSource
import com.trollingcont.filmsonline.repository.datasource.remote.RemoteDataSource
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : Repository {

    override fun getFilms(
        onSuccess: (List<Film>) -> Unit,
        onFailure: () -> Unit
    ) {
        val films = localDataSource.getFilms()

        if (films != null) {
            onSuccess(films)
        }
        else {
            remoteDataSource.getFilms(
                { filmsList ->
                    localDataSource.setFilms(filmsList)
                    onSuccess(filmsList)
                },
                { onFailure() }
            )
        }
    }

    override fun getBitmapByUrl(
        imageUrl: String,
        onSuccess: (Bitmap) -> Unit,
        onFailure: () -> Unit
    ) {
        val bitmap = localDataSource.getBitmapByUrl(imageUrl)

        if (bitmap != null) {
            onSuccess(bitmap)
        }
        else {
            remoteDataSource.getImageByUrl(
                imageUrl,
                { downloadedBitmap ->
                    localDataSource.setBitmapByUrl(imageUrl, downloadedBitmap)
                    onSuccess(downloadedBitmap)
                },
                { onFailure() }
            )
        }
    }
}