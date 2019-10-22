package com.sagon.boxotop.data.repository

import com.sagon.boxotop.data.apiModel.SearchAPI
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbWebService {

    @GET(".")
    fun getListFilmBySearch(@Query("s") searchKey : String, @Query("apiKey") apiKey : String, @Query("page") pageNumber : Int) : Flowable<SearchAPI>
}