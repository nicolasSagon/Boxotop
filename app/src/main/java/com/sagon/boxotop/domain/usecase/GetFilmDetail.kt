package com.sagon.boxotop.domain.usecase

import com.sagon.boxotop.data.repository.FilmRepository
import com.sagon.boxotop.domain.model.Film
import com.sagon.boxotop.domain.repository.BaseRepository
import io.reactivex.Flowable

class GetFilmDetail : BaseUseCase<Film, GetFilmDetail.Param>() {

    private val filmRepository: BaseRepository<Film> = FilmRepository()

    override fun getFlowable(param: Param): Flowable<Film> {
        return filmRepository.getSingleItem(param.id).toFlowable()
    }

    data class Param(
        val id: String
    )
}