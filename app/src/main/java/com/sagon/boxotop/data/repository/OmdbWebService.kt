package com.sagon.boxotop.data.repository

import com.sagon.boxotop.data.apiModel.FilmItemApi
import com.sagon.boxotop.data.apiModel.SearchAPI
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbWebService {

    @GET(".")
    fun getListFilmBySearch(@Query("s") searchKey: String, @Query("apiKey") apiKey: String, @Query("page") pageNumber: Int): Flowable<SearchAPI>

    @GET(".")
    fun getFilmById(@Query("i") id: String, @Query("apiKey") apiKey: String, @Query("plot") plotSize: String): Single<FilmItemApi>
}