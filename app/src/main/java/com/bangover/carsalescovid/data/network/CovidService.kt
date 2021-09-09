package com.bangover.carsalescovid.data.network

import com.bangover.carsalescovid.Core.RetrofitHelper
import com.bangover.carsalescovid.data.model.CovidModel
import com.bangover.carsalescovid.data.model.DataModel
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

class CovidService @Inject constructor(private val api: Retrofit){

    private val apiKey = "96afa298cbmsh913f910f914494cp110c39jsn01a32d68445e"

    fun getTotalReports(date:String): Single<CovidModel> {
        return api.create(CovidApiClient::class.java)
            .getTotalReports(apiKey, date)
            .subscribeOn(Schedulers.io())
    }
}