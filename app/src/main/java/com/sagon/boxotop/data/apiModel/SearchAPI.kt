package com.sagon.boxotop.data.apiModel

import com.squareup.moshi.Json

@Suppress("ArrayInDataClass")
data class SearchAPI(
    @field:Json(name = "Search")
    val search : Array<FilmItemApi>?,
    val totalResults : Int,
    @field:Json(name = "Response")
    val response : String

)
