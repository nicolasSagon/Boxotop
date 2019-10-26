package com.sagon.boxotop.domain.usecase

import com.sagon.boxotop.data.repository.FilmRepository
import com.sagon.boxotop.domain.model.Film
import com.sagon.boxotop.domain.repository.BaseRepository
import io.reactivex.Flowable

class GetListFilm : BaseUseCase<PaginedData<List<Film>>, GetListFilm.Param>() {

    private val filmRepository : BaseRepository<Film> = FilmRepository()

    override fun getFlowable(param: GetListFilm.Param): Flowable<PaginedData<List<Film>>> {
        return filmRepository.getListWithParameter(param.searchKey, param.currentPage)
                    .map {
                        PaginedData(param.currentPage, it)
                    }
    }

    data class Param (
        val searchKey: String,
        val currentPage: Int
    )


}