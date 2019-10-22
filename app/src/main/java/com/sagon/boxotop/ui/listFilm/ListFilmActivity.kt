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

class ListFilmActivity : BaseActivity() {

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
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager

        val scrollListener = object : EndlessRecyclerViewScrollListener(layoutManager){
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                loadNextData()
            }
        }
        recyclerView.addOnScrollListener(scrollListener)
    }

    fun loadNextData(){
        displayLoader(true)
        listFilmViewModel.getNextFilm().observe(this, filmObserver)
    }

    fun displayLoader(isDisplay : Boolean){
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
        if(!paginedFilms.data.isEmpty()){
            testDisplay.setVisible(false)
            recyclerView.setVisible(true)
            adapter.updateData(paginedFilms.data, paginedFilms.currentPage == 1)
        }
        else {
            recyclerView.setVisible(false)
            testDisplay.setVisible(true)
            testDisplay.text = "Pas de données"
        }
    }
}
