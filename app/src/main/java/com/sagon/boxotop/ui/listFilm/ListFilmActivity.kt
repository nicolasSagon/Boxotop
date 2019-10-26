package com.sagon.boxotop.ui.listFilm

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sagon.boxotop.R
import com.sagon.boxotop.domain.model.Film
import com.sagon.boxotop.domain.usecase.PaginedData
import com.sagon.boxotop.extensions.setVisible
import com.sagon.boxotop.ui.BaseActivity
import com.sagon.boxotop.ui.listFilm.detailsFilm.DetailsFilmActivity
import kotlinx.android.synthetic.main.activity_list_film.*


class ListFilmActivity : BaseActivity(), FilmAdapter.OnEndOfRecyclerViewReachedListener,
    FilmAdapter.OnItemSelectedListener {

    private lateinit var listFilmViewModel: ListFilmViewModel
    private lateinit var adapter: FilmAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_film)

        listFilmViewModel = getViewModel(this)

        submitButton.setOnClickListener {
            if(!searchTitle.text.isNullOrEmpty()) {
                displayLoader(true)
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(mainLayout.windowToken, 0)
                listFilmViewModel.getListFilmBySearchKey(searchTitle.text.toString(), true).observe(this, filmObserver)
            }
        }

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = RecyclerView.VERTICAL
        adapter = FilmAdapter()
        adapter.addOnEndOfRecyclerViewReachedListener(this)
        adapter.addOnItemSelectedListener(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager
    }

    override fun onLastElementReached() {
        displayLoader(true)
        listFilmViewModel.getNextFilm().observe(this, filmObserver)
    }

    override fun onItemSelected(film: Film?) {
        if (film != null) {
            val intent = DetailsFilmActivity.createIntent(this, film.imdbID)
            startActivity(intent)
        }
    }

    private fun displayLoader(isDisplay : Boolean){
        if(isDisplay){
            progressBar.setVisible(true)
            testDisplay.setVisible(false)
        }
        else {
            progressBar.setVisible(false)
        }
    }

    private val filmObserver = Observer<PaginedData<List<Film>>> {
        displayFilms(it)
    }

    private fun displayFilms(paginedFilms : PaginedData<List<Film>>){
        displayLoader(false)
        if(paginedFilms.data.isEmpty() && paginedFilms.currentPage == 1){
            recyclerView.setVisible(false)
            testDisplay.setVisible(true)
            testDisplay.text = getString(R.string.no_data_found)
        }
        else {
            testDisplay.setVisible(false)
            recyclerView.setVisible(true)

            //Prevent infinite loading when no more result
            if(!paginedFilms.data.isEmpty()){
                adapter.updateData(paginedFilms.data, paginedFilms.currentPage == 1)
            }
        }
    }
}
