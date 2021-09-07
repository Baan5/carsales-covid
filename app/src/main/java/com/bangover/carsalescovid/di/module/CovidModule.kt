package com.bangover.carsalescovid.di.module

import com.bangover.carsalescovid.Core.RetrofitHelper
import com.bangover.carsalescovid.data.network.CovidApiClient
import com.bangover.carsalescovid.ui.viewModel.CovidViewModel
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class CovidModule {

    private val baseUrl = "https://covid-19-statistics.p.rapidapi.com/reports/"

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideCovidApiClient(retrofit: Retrofit): CovidApiClient{
        return retrofit.create(CovidApiClient::class.java)
    }

}