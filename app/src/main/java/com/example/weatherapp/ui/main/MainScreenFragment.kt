package com.example.weatherapp.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentMainScreenBinding
import com.example.weatherapp.di.DaggerMainScreenComponent
import com.example.weatherapp.di.RepositoryModule
import javax.inject.Inject


class MainScreenFragment : Fragment(), LifecycleObserver, DefaultLifecycleObserver {
    private lateinit var binding: FragmentMainScreenBinding
    @Inject
    lateinit var viewModel: MainScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_main_screen, container, false
        )

        val thisContext: Context = container?.context!!
        DaggerMainScreenComponent.builder()
            .repositoryModule(RepositoryModule(thisContext))
            .build()
            .inject(this)

        binding.mainScreen = viewModel
        binding.mainScreenFragment = this
        binding.lifecycleOwner = this
        lifecycle.addObserver(viewModel)

        viewModel.getCurrentWeatherForCity()
        //viewModel.getCurrentWeatherForLocation()
        viewModel.getForecastForCity()
        viewModel.status.observe(viewLifecycleOwner, Observer<Boolean> { status ->
            if (!status) {
                Toast.makeText(activity, "There is no such city!", Toast.LENGTH_LONG).show()
            }
        })

        return binding.root
    }


    fun goToCities(){
        val action = MainScreenFragmentDirections.navigateToCities()
        Navigation.findNavController(binding.root).navigate(action)
    }

}