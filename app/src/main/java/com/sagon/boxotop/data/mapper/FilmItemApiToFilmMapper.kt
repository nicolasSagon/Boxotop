package com.sagon.boxotop.data.mapper

import com.sagon.boxotop.data.apiModel.FilmItemApi
import com.sagon.boxotop.domain.model.Film
import com.sagon.boxotop.domain.model.Rating

class FilmItemApiToFilmMapper : BaseMapper<FilmItemApi, Film> {

    override fun convert(input: FilmItemApi): Film {

        val listRatings = mutableListOf<Rating>()
        for (ratingApi in input.ratingApis) {
            listRatings.add(RatingImplementation(ratingApi.source, ratingApi.value))
        }

        return FilmDetailsImplementation(
            input.title,
            input.year,
            input.rated,
            input.released,
            input.runtime,
            input.genre,
            input.director,
            input.writer,
            input.actors,
            input.plot,
            input.language,
            input.country,
            input.awards,
            input.poster,
            listRatings,
            input.metascore,
            input.imdbRating.toFloatOrNull(),
            input.imdbVotes,
            input.imdbID,
            input.type,
            input.dvd,
            input.boxOffice,
            input.production,
            input.website
        )
    }

    private data class FilmDetailsImplementation(
        override val title: String,
        override val year: String,
        override val rated: String?,
        override val released: String?,
        override val runtime: String?,
        override val genre: String?,
        override val director: String?,
        override val writer: String?,
        override val actors: String?,
        override val plot: String?,
        override val language: String?,
        override val country: String?,
        override val awards: String?,
        override val poster: String,
        override val ratings: List<Rating>?,
        override val metascore: String?,
        override val imdbRating: Float?,
        override val imdbVotes: String?,
        override val imdbID: String,
        override val type: String,
        override val dvd: String?,
        override val boxOffice: String?,
        override val production: String?,
        override val website: String?
    ) : Film

    private data class RatingImplementation(
        override val source: String,
        override val value: String
    ) : Rating
}