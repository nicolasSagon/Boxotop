package com.sagon.boxotop.ui.listFilm.detailsFilm

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.sagon.boxotop.R
import com.sagon.boxotop.domain.model.Film
import com.sagon.boxotop.extensions.setVisible
import com.sagon.boxotop.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_details_film.*

class DetailsFilmActivity : BaseActivity() {

    private lateinit var detailsFilmViewModel: DetailsFilmViewModel

    companion object {

        private const val EXTRA_FILM_ID = "EXTRA_FILM_ID"

        fun createIntent(context: Context, id: String): Intent {
            val intent = Intent(context, DetailsFilmActivity::class.java)
            intent.putExtra(EXTRA_FILM_ID, id)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_film)

        detailsFilmViewModel = getViewModel(this)
        detailsFilmViewModel.getFilmById(intent.getStringExtra(EXTRA_FILM_ID)).observe(this, filmObserver)
    }

    private val filmObserver = Observer<Film> {
        displayFilm(it)
    }

    private fun displayFilm(film: Film) {
        filmTitle.text = film.title
    }

    private fun displayLoader(isDisplay: Boolean) {
        if (isDisplay) {
            progressBar.setVisible(true)
        } else {
            progressBar.setVisible(false)
        }
    }

}