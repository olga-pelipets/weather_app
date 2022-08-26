package com.example.weatherapp.ui.additionalinfo

import android.util.Log
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.domain.models.ForecastWeather
import com.example.weatherapp.domain.Result
import com.example.weatherapp.domain.repo.WeatherRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


class AdditionalInfoScreenViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel(), LifecycleObserver {
    val forecast = MutableLiveData<ForecastWeather?>()

    init {
        getCache()
    }

    private fun getCache() {
        weatherRepository.getForecastWeather()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    when (it) {
                        is Result.OnSuccess -> {
                            forecast.value = it.data
                        }
                        is Result.OnError -> {
                            Log.i("result", "error")
                        }
                    }
                })
    }
}