package com.sagon.boxotop.data.mapper

//T input type
//P output type

interface BaseMapper<T, P> {

    fun convert(input : T) : P

}