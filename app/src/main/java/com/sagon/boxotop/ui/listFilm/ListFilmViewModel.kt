package com.sagon.boxotop.ui.listFilm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sagon.boxotop.domain.model.Film
import com.sagon.boxotop.domain.usecase.GetListFilm
import com.sagon.boxotop.domain.usecase.PaginedData

class ListFilmViewModel(application: Application) : AndroidViewModel(application) {

    private var getListFilm = GetListFilm()
    private var lastSearchKey : String? = null
    private var currentPage: Int = 1

    fun getListFilmBySearchKey(searchKey : String, isFirstLoad : Boolean): LiveData<PaginedData<List<Film>>> {
        lastSearchKey = searchKey
        if(isFirstLoad) {
            currentPage = 1
        }
        val liveData = getListFilm.buildLiveData(GetListFilm.Param(searchKey, currentPage))
        currentPage++
        return liveData
    }

    fun getNextFilm() : LiveData<PaginedData<List<Film>>>{
        return if(lastSearchKey != null){
            val liveData = getListFilm.buildLiveData((GetListFilm.Param(lastSearchKey!!, currentPage)))
            currentPage++
            liveData
        } else {
            MutableLiveData()
        }
    }

}
