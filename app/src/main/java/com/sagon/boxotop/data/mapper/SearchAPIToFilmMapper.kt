package com.sagon.boxotop.data.mapper

import com.sagon.boxotop.data.apiModel.SearchAPI
import com.sagon.boxotop.domain.model.Film
import com.sagon.boxotop.domain.model.Rating

class SearchAPIToFilmMapper : BaseMapper<SearchAPI, List<Film>> {

    override fun convert(input: SearchAPI): List<Film> {

        val result = mutableListOf<Film>()

        input.search?.forEach {
            result.add(FilmImplementation(it.title, it.year, it.imdbID, it.type, it.poster))
        }

        return result
    }


    private data class FilmImplementation(
        override val title: String,
        override val year: String,
        override val imdbID: String,
        override val type: String,
        override val poster: String
    ) : Film {
        override val rated: String? = null
        override val released: String? = null
        override val runtime: String? = null
        override val genre: String? = null
        override val director: String? = null
        override val writer: String? = null
        override val actors: String? = null
        override val plot: String? = null
        override val language: String? = null
        override val country: String? = null
        override val awards: String? = null
        override val ratings: List<Rating>? = null
        override val metascore: String? = null
        override val imdbRating: Float? = null
        override val imdbVotes: String? = null
        override val dvd: String? = null
        override val boxOffice: String? = null
        override val production: String? = null
        override val website: String? = null
    }

}