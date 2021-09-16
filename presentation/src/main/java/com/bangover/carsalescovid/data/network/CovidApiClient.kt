package com.bangover.carsalescovid.data.network

import com.bangover.carsalescovid.data.model.CovidModel
import com.bangover.carsalescovid.data.model.DataModel
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CovidApiClient {

    @GET("total")
    suspend fun getTotalReports(@Header("X-RapidAPI-Key") apiKey:String, @Query("date") date:String): Response<CovidModel>

}