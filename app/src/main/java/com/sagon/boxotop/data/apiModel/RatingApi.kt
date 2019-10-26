package com.sagon.boxotop.data.apiModel

import com.squareup.moshi.Json

data class RatingApi(
    @field:Json(name = "Source")
    val source: String,
    @field:Json(name = "Value")
    val value: String
)