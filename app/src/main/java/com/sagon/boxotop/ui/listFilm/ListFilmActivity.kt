package com.sagon.boxotop.ui.listFilm

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sagon.boxotop.R
import com.sagon.boxotop.domain.model.Film
import com.sagon.boxotop.domain.usecase.PaginedData
import com.sagon.boxotop.extensions.setVisible
import com.sagon.boxotop.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class ListFilmActivity : BaseActivity(), FilmAdapter.OnEndOfRecyclerViewReachedListener {

    private lateinit var listFilmViewModel: ListFilmViewModel
    private lateinit var adapter: FilmAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listFilmViewModel = getViewModel(this)

        submitButton.setOnClickListener {
            if(!searchTitle.text.isNullOrEmpty()) {
                displayLoader(true)
                listFilmViewModel.getListFilmBySearchKey(searchTitle.text.toString(), true).observe(this, filmObserver)
            }
        }

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = RecyclerView.VERTICAL
        adapter = FilmAdapter()
        adapter.addOnEndOfRecyclerViewReachedListener(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager
    }

    override fun onLastElementReached() {
        displayLoader(true)
        listFilmViewModel.getNextFilm().observe(this, filmObserver)
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
            testDisplay.text = "Pas de données"
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
