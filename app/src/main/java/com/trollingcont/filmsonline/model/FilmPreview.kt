package com.trollingcont.filmsonline.model

import android.graphics.Bitmap

data class FilmPreview(
    val id: Int,
    val name: String?,
    var bitmap: Bitmap?
)

class FilmPreviewComparator : Comparator<FilmPreview> {

    override fun compare(o1: FilmPreview?, o2: FilmPreview?): Int =
        if (o1?.name == null) {
            if (o2?.name == null) {
                0
            } else {
                -1
            }
        } else {
            if (o2?.name == null) {
                1
            } else {
                o1.name.compareTo(o2.name, true)
            }
        }

}
