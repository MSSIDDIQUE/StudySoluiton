package com.baymax.studysolutions.ui.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.baymax.studysolutions.R
import com.baymax.studysolutions.data.network.*
import com.baymax.studysolutions.ui.weather.current.CurrentWeatherViewModel
import com.baymax.studysolutions.ui.weather.current.CurrentWeatherViewModelFactory
import com.baymax.studysolutions.utils.FragmentScope
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinContext
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import org.kodein.di.generic.kcontext

class HomeFragment : FragmentScope(), KodeinAware {

    override val kodeinContext = kcontext<Fragment>(this)
    override val kodein by kodein()

    private val viewModelFactory: CurrentWeatherViewModelFactory by instance()

    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this,viewModelFactory).get(CurrentWeatherViewModel::class.java)

        bindUI()
    }

    private fun bindUI()= launch{
        val currentWeather = viewModel.weather.await()
        currentWeather.observe(this@HomeFragment, Observer {
            if(it == null) return@Observer
            group_loading.visibility = View.GONE
            imageView.visibility = View.VISIBLE
            textView_temperature.text = it.temperature.toString()+"°C"
            location.text = "Banglore"
            Picasso.get().load(it.weatherIcons[0]).into(imageView_condition_icon)
            Picasso.get().load(R.drawable.banner).centerCrop().resize(360,340).into(imageView)
        })
    }

}
