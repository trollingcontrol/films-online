package com.trollingcont.filmsonline.view

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.trollingcont.filmsonline.MyApplication
import com.trollingcont.filmsonline.R
import com.trollingcont.filmsonline.contract.FilmDescriptionContract
import com.trollingcont.filmsonline.databinding.FragmentFilmDescriptionBinding
import com.trollingcont.filmsonline.di.FilmDescriptionComponent
import javax.inject.Inject

class FilmDescriptionFragment : Fragment(), FilmDescriptionContract.View {

    private lateinit var binding: FragmentFilmDescriptionBinding

    private lateinit var filmDescriptionComponent: FilmDescriptionComponent

    private var filmId: Int = -1

    @Inject
    lateinit var filmDescriptionPresenter: FilmDescriptionContract.Presenter

    companion object {
        fun newInstance(filmId: Int): FilmDescriptionFragment {
            val newFragment = FilmDescriptionFragment()

            newFragment.setFilmId(filmId)

            return newFragment
        }
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

        loadFilmDescription()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        filmDescriptionPresenter.detach()
    }

    fun setFilmId(id: Int) {
        filmId = id
    }

    private fun loadFilmDescription() {
        filmDescriptionPresenter.loadFilmDescription(filmId)
    }

    override fun setFilmDescription(
        localizedName: String?,
        name: String?,
        year: Int?,
        rating: Float?,
        description: String?
    ) {
        binding.filmLocalizedName.text =
            localizedName ?: getString(R.string.missing_localized_name)

        binding.filmName.text =
            name ?: getString(R.string.missing_name)

        binding.filmYear.text =
            getString(
                R.string.year_pattern,
                year ?: getString(R.string.missing_year)
            )

        binding.filmRating.text =
            getString(
                R.string.rating_pattern,
                rating ?: getString(R.string.missing_rating)
            )
        binding.filmDescription.text =
            description ?: getString(R.string.missing_description)
    }

    override fun setFilmBitmap(bitmap: Bitmap) {
        binding.noImageText.visibility = View.GONE
        binding.filmImage.setImageBitmap(bitmap)
    }

    override fun onError(throwable: Throwable) {
        Toast.makeText(requireContext(), throwable.toString(), Toast.LENGTH_LONG).show()
    }
}