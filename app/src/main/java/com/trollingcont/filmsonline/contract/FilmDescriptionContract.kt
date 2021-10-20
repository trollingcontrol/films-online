package com.trollingcont.filmsonline.contract

import android.graphics.Bitmap
import com.trollingcont.filmsonline.model.Film

interface FilmDescriptionContract {

    interface View {
        fun setFilmDescription(
            localizedName: String?,
            name: String?,
            year: Int?,
            rating: Float?,
            description: String?
        )

        fun setFilmBitmap(bitmap: Bitmap)
    }

    interface Presenter {
        fun loadFilmDescription(id: Int)

        fun attach(view: View)

        fun detach()
    }

    interface Model {
        fun getFilmById(
            id: Int,
            onSuccess: (Film) -> Unit,
            onFailure: () -> Unit
        )

        fun getFilmImageByUrl(
            imageUrl: String,
            onSuccess: (Bitmap) -> Unit,
            onFailure: () -> Unit
        )

        fun setFilmId(id: Int)

        fun getFilmId(): Int
    }
}