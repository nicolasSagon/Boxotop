package com.sagon.boxotop.domain.usecase

data class PaginedData<T> (
    val currentPage : Int,
    val data: T
)