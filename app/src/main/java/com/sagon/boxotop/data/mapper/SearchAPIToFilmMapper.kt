package com.sagon.boxotop.data.mapper

import com.sagon.boxotop.data.apiModel.SearchAPI
import com.sagon.boxotop.domain.model.Film

class SearchAPIToFilmMapper : BaseMapper<SearchAPI, List<Film>> {

    override fun convert(input: SearchAPI): List<Film> {

        val result = mutableListOf<Film>()

        input.search?.forEach {
            result.add(FilmImplementation(it.title, it.Year, it.imdbID, it.type, it.poster))
        }

        return result
    }


    private data class FilmImplementation(
        override val title: String,
        override val Year: String,
        override val imdbID: String,
        override val type: String,
        override val poster: String
    ) : Film

}