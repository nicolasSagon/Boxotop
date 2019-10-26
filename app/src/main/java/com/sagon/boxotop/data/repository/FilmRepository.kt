package com.sagon.boxotop.data.repository

import com.sagon.boxotop.data.mapper.FilmItemApiToFilmMapper
import com.sagon.boxotop.data.mapper.SearchAPIToFilmMapper
import com.sagon.boxotop.domain.model.Film
import com.sagon.boxotop.domain.repository.BaseRepository
import io.reactivex.Flowable
import io.reactivex.Single

class FilmRepository : BaseRepository<Film> {

    private val omdbWebService = RetrofitInstance.omdbWebService
    private val searchResultMapper = SearchAPIToFilmMapper()
    private val filmMapper = FilmItemApiToFilmMapper()

    override fun getList(): Flowable<List<Film>> {
        TODO("not implemented")
    }

    override fun getListWithParameter(parameter : String, page : Int) : Flowable<List<Film>> {
        return omdbWebService.getListFilmBySearch(searchKey = parameter, apiKey = RetrofitInstance.apiKey, pageNumber = page ).map {
            searchResultMapper.convert(it)
        }
    }

    override fun getSingleItem(id: String): Single<Film> {
        return omdbWebService.getFilmById(id = id, apiKey = RetrofitInstance.apiKey, plotSize = "full").map {
            filmMapper.convert(it)
        }
    }

}