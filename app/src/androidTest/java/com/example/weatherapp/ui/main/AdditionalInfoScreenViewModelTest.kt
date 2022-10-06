package com.example.weatherapp.ui.main

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.weatherapp.data.api.RetrofitClient
import com.example.weatherapp.data.repo.StorageRepositoryImpl
import com.example.weatherapp.data.repo.WeatherRepositoryImpl
import com.example.weatherapp.domain.models.LocationMethod
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.Before
import org.junit.Test
import kotlinx.coroutines.test.runTest
import com.google.common.truth.Truth.assertThat
import android.content.res.Resources
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.weatherapp.ui.additionalinfo.AdditionalInfoScreenViewModel
import org.junit.Rule

@ExperimentalCoroutinesApi
class AdditionalInfoScreenViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: AdditionalInfoScreenViewModel
    lateinit var storageRepository: StorageRepositoryImpl

    @Before
    fun setup(){
        storageRepository = StorageRepositoryImpl(ApplicationProvider.getApplicationContext())
        val retrofitClient = RetrofitClient()
        val weatherRepository = WeatherRepositoryImpl(retrofitClient, storageRepository)
        viewModel = AdditionalInfoScreenViewModel(weatherRepository)
    }

    @Test
    fun fetchData() = runTest{
        storageRepository.saveLocationMethod(LocationMethod.City)
        storageRepository.saveCity("Warsaw")
        viewModel.fetchData()
        viewModel.forecast.observeForever { data ->
            assertThat(data?.city).isEqualTo("Warsaw")
        }
    }
}