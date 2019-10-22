package com.sagon.boxotop.domain.repository

import com.sagon.boxotop.domain.model.Film
import io.reactivex.Flowable
import io.reactivex.Single

interface BaseRepository<T> {

    fun getList() : Flowable<List<T>>

    //pourrait être amélioré avec une classe pour faire passer les paramètres facilement utilisable
    fun getListWithParameter(parameter : String, page: Int) : Flowable<List<Film>>

    fun getSingleItem() : Single<T>

}