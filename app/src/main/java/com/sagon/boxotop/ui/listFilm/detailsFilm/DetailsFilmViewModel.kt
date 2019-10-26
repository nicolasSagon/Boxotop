package com.sagon.boxotop.ui.listFilm.detailsFilm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.sagon.boxotop.domain.model.Film
import com.sagon.boxotop.domain.usecase.GetFilmDetail

class DetailsFilmViewModel(application: Application) : AndroidViewModel(application) {

    private val getFilmDetail = GetFilmDetail()

    fun getFilmById(id: String): LiveData<Film> {
        return getFilmDetail.buildLiveData(GetFilmDetail.Param(id))
    }

}