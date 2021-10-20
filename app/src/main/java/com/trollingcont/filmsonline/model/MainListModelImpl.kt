package com.trollingcont.filmsonline.model

import android.graphics.Bitmap
import android.util.Log
import com.trollingcont.filmsonline.contract.MainListContract
import com.trollingcont.filmsonline.repository.Repository
import javax.inject.Inject

class MainListModelImpl @Inject constructor(
    val repository: Repository
) : MainListContract.Model {

    private var selectedGenre: String? = null

    override fun getFilms(
        onSuccess: (List<Film>) -> Unit,
        onFailure: () -> Unit
    ) {
        repository.getFilms(onSuccess, onFailure)
    }

    override fun getFilmImageByUrl(
        imageUrl: String,
        onSuccess: (Bitmap) -> Unit,
        onFailure: () -> Unit
    ) {
        repository.getBitmapByUrl(imageUrl, onSuccess, onFailure)
    }

    override fun getSelectedGenre(): String? =
        selectedGenre

    override fun setSelectedGenre(genreName: String) {
        this.selectedGenre = genreName
    }

}