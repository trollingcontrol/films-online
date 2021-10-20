package com.trollingcont.filmsonline.view

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.trollingcont.filmsonline.MyApplication
import com.trollingcont.filmsonline.contract.FilmDescriptionContract
import com.trollingcont.filmsonline.databinding.FragmentFilmDescriptionBinding
import com.trollingcont.filmsonline.di.FilmDescriptionComponent
import javax.inject.Inject

class FilmDescriptionFragment(
    private val filmId: Int
    ) : Fragment(), FilmDescriptionContract.View {

    private lateinit var binding: FragmentFilmDescriptionBinding

    private lateinit var filmDescriptionComponent: FilmDescriptionComponent

    @Inject
    lateinit var filmDescriptionPresenter: FilmDescriptionContract.Presenter

    companion object {
        fun newInstance(filmId: Int) = FilmDescriptionFragment(filmId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilmDescriptionBinding.inflate(inflater, container, false)

        filmDescriptionComponent = (requireActivity().application as MyApplication)
            .appComponent.filmDescriptionComponent().create()

        filmDescriptionComponent.inject(this)

        filmDescriptionPresenter.attach(this)

        loadFilmDescription(filmId)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        filmDescriptionPresenter.detach()
    }

    private fun loadFilmDescription(id: Int) {
        filmDescriptionPresenter.loadFilmDescription(id)
    }

    override fun setFilmDescription(
        localizedName: String,
        name: String,
        year: Int,
        rating: Float?,
        description: String
    ) {
        binding.filmLocalizedName.text = localizedName
        binding.filmName.text = name
        binding.filmYear.text = year.toString()
        binding.filmRating.text = if (rating != null) {
            rating.toString()
        }
        else {
            "отсутствует"
        }
        binding.filmDescription.text = description
    }

    override fun setFilmBitmap(bitmap: Bitmap) {
        binding.noImageText.visibility = View.GONE
        binding.filmImage.setImageBitmap(bitmap)
    }
}