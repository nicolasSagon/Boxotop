package com.sagon.boxotop.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import com.sagon.boxotop.data.repository.FilmRepository
import com.sagon.boxotop.domain.model.Film
import com.sagon.boxotop.domain.repository.BaseRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GetListFilm : BaseUseCase<LiveData<PaginedData<List<Film>>>, GetListFilm.Param> {

    private val filmRepository : BaseRepository<Film> = FilmRepository()

    override fun getLiveData(param : GetListFilm.Param): LiveData<PaginedData<List<Film>>> {
        return LiveDataReactiveStreams.fromPublisher(
            filmRepository.getListWithParameter(param.searchKey, param.currentPage)
                .map {
                    PaginedData(param.currentPage, it)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    data class Param (
        val searchKey: String,
        val currentPage: Int
    )


}