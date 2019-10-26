package com.sagon.boxotop.ui.listFilm.detailsFilm

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.sagon.boxotop.R
import com.sagon.boxotop.domain.model.Film
import com.sagon.boxotop.extensions.setVisible
import com.sagon.boxotop.ui.BaseActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details_film.*

class DetailsFilmActivity : BaseActivity() {

    private lateinit var detailsFilmViewModel: DetailsFilmViewModel
    private val picasso = Picasso.get()

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
        displayLoader(true)
    }

    private val filmObserver = Observer<Film> {
        displayLoader(false)
        displayFilm(it)
    }

    private fun displayFilm(film: Film) {
        filmTitle.text = film.title
        picasso.load(film.poster).into(filmImageView)
        releaseDate.text = getString(R.string.film_release_date, film.released)
        filmDuration.text = getString(R.string.film_duration, film.runtime)
        filmGenre.text = getString(R.string.film_genre, film.genre)
        filmWriter.text = getString(R.string.film_writer, film.writer)
        filmActors.text = getString(R.string.film_actors, film.actors)
        synopsisHolder.text = film.plot
        if (film.imdbRating != null) {
            filmRatingBar.rating = film.imdbRating!! / 2
        } else {
            filmRatingBar.setVisible(false)
        }

    }

    private fun displayLoader(isDisplay: Boolean) {
        if (isDisplay) {
            progressBar.setVisible(true)
        } else {
            progressBar.setVisible(false)
        }
    }

}