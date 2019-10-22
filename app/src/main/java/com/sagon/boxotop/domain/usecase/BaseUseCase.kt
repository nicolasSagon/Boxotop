package com.sagon.boxotop.domain.usecase

//T is the parameter for the usecase
interface BaseUseCase<out LiveData, T> {

    fun getLiveData(param : T)  : LiveData

}