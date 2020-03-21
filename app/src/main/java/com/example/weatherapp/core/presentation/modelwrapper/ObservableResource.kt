package com.example.weatherapp.core.presentation.modelwrapper

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer


class ObservableResource<T> : SingleLiveEvent<T>() {

    val error: SingleLiveEvent<Throwable> = SingleLiveEvent()
    val loading: SingleLiveEvent<Boolean> = SingleLiveEvent()

    fun observe(
        owner: LifecycleOwner, successObserver: Observer<T>,
        loadingObserver: Observer<Boolean>? = null,
        commonErrorObserver: Observer<Throwable>
    ) {
        super.observe(owner, successObserver)
        loadingObserver?.let { loading.observe(owner, it) }
        error.observe(owner, commonErrorObserver)
    }
}