package com.bangover.carsalescovid.data

import com.bangover.carsalescovid.data.model.CovidModel
import com.bangover.carsalescovid.data.model.DataModel
import com.bangover.carsalescovid.data.network.CovidService
import io.reactivex.Single
import javax.inject.Inject

class CovidRepository @Inject constructor(val api: CovidService) {

    //val api = CovidService()

    fun getTotalReports(date:String): Single<CovidModel> {
        return api.getTotalReports(date)
    }

}