package com.trollingcont.filmsonline.view

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.trollingcont.filmsonline.MyApplication
import com.trollingcont.filmsonline.contract.MainListContract
import com.trollingcont.filmsonline.databinding.FragmentMainListBinding
import com.trollingcont.filmsonline.di.MainListComponent
import com.trollingcont.filmsonline.model.FilmPreview
import com.trollingcont.filmsonline.rv.MainListAdapter
import javax.inject.Inject

class MainListFragment : Fragment(), MainListContract.View {

    private lateinit var binding: FragmentMainListBinding
    private var mainListAdapter: MainListAdapter? = null

    lateinit var mainListComponent: MainListComponent

    @Inject
    lateinit var mainListPresenter: MainListContract.Presenter

    companion object {
        fun newInstance() = MainListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainListBinding.inflate(inflater, container, false)

        mainListComponent = (requireActivity().application as MyApplication)
                .appComponent.mainListComponent().create()

        mainListComponent.inject(this)

        mainListAdapter = MainListAdapter { clickedElementType, id ->
            onClickMainList(clickedElementType, id)
        }

        binding.mainListRecyclerView.adapter = mainListAdapter

        mainListPresenter.attach(this)

        loadFilms()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mainListPresenter.detach()
    }

    override fun updateGenresList(genres: List<String>) {
        mainListAdapter?.setGenresList(genres)
    }

    override fun setFilmPreviewList(filmPreviews: List<FilmPreview>) {
        mainListAdapter?.setFilmsList(filmPreviews)
    }

    override fun setFilmPreviewBitmap(filmId: Int, bitmap: Bitmap) {
        mainListAdapter?.setFilmBitmap(filmId, bitmap)
    }

    override fun highlightGenre(genreName: String) {
        mainListAdapter?.highlightGenre(genreName)
    }

    override fun removeHighlightFromGenre(genreName: String) {
        mainListAdapter?.removeHighlightFromGenre(genreName)
    }

    private fun loadFilms() {
        mainListPresenter.loadMainList()
    }

    private fun onClickMainList(clickedElementType: Int, id: String) {
        when (clickedElementType) {
            MainListAdapter.ITEM_TYPE_GENRE -> mainListPresenter.selectGenre(id)
            MainListAdapter.ITEM_TYPE_FILMS -> openFilmDescriptionFragment(id)
        }
    }

    private fun openFilmDescriptionFragment(filmName: String) {

    }
}