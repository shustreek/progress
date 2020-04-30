package com.amachikhin.testapplication.features.currentweather.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amachikhin.testapplication.features.base.domain.CurrentWeatherRes
import com.amachikhin.testapplication.features.base.presentation.BaseViewModel
import com.amachikhin.testapplication.features.currentweather.domain.CurrentWeatherInteractor
import com.amachikhin.testapplication.features.currentweather.domain.CurrentWeatherInteractorOut
import com.amachikhin.testapplication.utils.SingleLiveEvent
import javax.inject.Inject

class CurrentWeatherViewModel @Inject constructor(
    interactor: CurrentWeatherInteractor
) : BaseViewModel<CurrentWeatherInteractor>(interactor), CurrentWeatherInteractorOut {

    private val mutLoading = MutableLiveData<Boolean>()
    private val mutError = SingleLiveEvent<Boolean>()
    private val mutData = MutableLiveData<CurrentWeatherRes>()

    val loading: LiveData<Boolean> get() = mutLoading
    val error: LiveData<Boolean> get() = mutError
    val data: LiveData<CurrentWeatherRes> get() = mutData

    init {
        interactor.setupInteractorOut(this)
        interactor.loadWeather()
    }

    override fun isLoading(loading: Boolean) {
        mutLoading.value = loading
    }

    override fun onLoaded(weather: CurrentWeatherRes) {
        mutData.value = weather
    }

    override fun onError(e: Throwable) {
        mutError.setValue(true)
        e.printStackTrace()
    }

    fun reloadData() {
        interactor.loadWeather()
    }
}
