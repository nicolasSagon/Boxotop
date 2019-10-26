package com.sagon.boxotop.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class BaseUseCase<T, P> {

    protected abstract fun getFlowable(param : P)  : Flowable<T>

    fun buildLiveData(param : P) : LiveData<T> {
        return LiveDataReactiveStreams.fromPublisher(getFlowable(param)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
        )
    }

}