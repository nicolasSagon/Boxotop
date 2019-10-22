package com.sagon.boxotop.data.apiModel

import com.squareup.moshi.Json

data class FilmItemApi(
    @field:Json(name = "Title")
    val title : String,
    @field:Json(name = "Year")
    val Year : String,
    val imdbID : String,
    @field:Json(name = "Type")
    val type : String,
    @field:Json(name = "Poster")
    val poster : String
)