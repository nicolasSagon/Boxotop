package com.sagon.boxotop.domain.usecase

//TODO degager cette merde
data class PaginedData<T> (
    val currentPage : Int,
    val data: T
)