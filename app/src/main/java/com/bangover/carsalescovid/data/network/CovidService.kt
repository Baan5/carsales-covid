package com.bangover.carsalescovid.data.network

import com.bangover.carsalescovid.Core.RetrofitHelper
import com.bangover.carsalescovid.data.model.CovidModel
import com.bangover.carsalescovid.data.model.DataModel
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class CovidService {

    private val retrofit = RetrofitHelper.getRetrofit()
    private val apiKey = "96afa298cbmsh913f910f914494cp110c39jsn01a32d68445e"

    fun getTotalReports(date:String): Single<CovidModel> {
        return retrofit.create(CovidApiClient::class.java)
            .getTotalReports(apiKey, date)
            .subscribeOn(Schedulers.io())
    }
}