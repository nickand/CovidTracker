package com.example.covidtracker.ui.home

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.Animation
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.example.covidtracker.R
import com.example.covidtracker.data.ApiClient
import com.example.covidtracker.di.getGlideUrl
import com.example.covidtracker.extensions.gone
import com.example.covidtracker.extensions.visible
import com.example.covidtracker.util.Constants
import com.example.covidtracker.util.Constants.BASE_URL
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.ext.android.getViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.coroutines.CoroutineContext

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homeViewModel.model.observe(this, Observer(::updateUi))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    private fun loadBanner() {
        Glide.with(this)
            .load(getGlideUrl())
            .into(bannerImage)
    }

    private fun updateUi(model: HomeViewModel.UiModel) {
        when (model) {
            is HomeViewModel.UiModel.Loading -> {
                progressBar.visible()
            }
            is HomeViewModel.UiModel.Content -> {
                progressBar.gone()

                loadBanner()

                Log.d("TAG", "RESPONSE: "+model.worldStat.toString())
            }
            is HomeViewModel.UiModel.Navigation -> {

            }
        }
    }
}
