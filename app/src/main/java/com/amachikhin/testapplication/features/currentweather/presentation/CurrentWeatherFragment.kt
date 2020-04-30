package com.amachikhin.testapplication.features.currentweather.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.amachikhin.testapplication.ICON_URL
import com.amachikhin.testapplication.R
import com.amachikhin.testapplication.di.AppComponent
import com.amachikhin.testapplication.features.base.presentation.BaseFragment
import com.amachikhin.testapplication.features.base.presentation.observe
import com.amachikhin.testapplication.features.currentweather.di.CurrentWeatherComponent
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.current_weather_fragment.*
import javax.inject.Inject

class CurrentWeatherFragment : BaseFragment<CurrentWeatherViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override val layout = R.layout.current_weather_fragment

    override val viewModel: CurrentWeatherViewModel by viewModels(factoryProducer = { viewModelFactory })

    override fun injectDependencies(appComponent: AppComponent) {
        CurrentWeatherComponent.Initializer.init(appComponent)
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_weather_fragment, container, false)
    }

    override fun initView(view: View) {
        swipeToRefresh.setColorSchemeResources(R.color.colorAccent)
        swipeToRefresh.setOnRefreshListener { viewModel.reloadData() }
    }

    override fun initObservers() {
        observe(viewModel.loading) {
            swipeToRefresh.isRefreshing = it
        }

        observe(viewModel.error) {
            view?.let {
                Snackbar.make(it, "Во Время загрузки произошла ошибка", Snackbar.LENGTH_SHORT).show()
            }
        }

        observe(viewModel.data) {
            cityName.text = it.name
            Picasso.get()
                .load(String.format(ICON_URL, it.icon))
                .into(weather)
            temp.text = getString(R.string.cw_temp, it.temp)
            feelsTemp.text = getString(R.string.cw_temp, it.feelsLike)
        }
    }

}
