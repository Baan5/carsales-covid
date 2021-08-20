package com.bangover.carsalescovid.data

import com.bangover.carsalescovid.data.model.CovidModel
import com.bangover.carsalescovid.data.model.DataModel
import com.bangover.carsalescovid.data.network.CovidService
import io.reactivex.Single

class CovidRepository {

    val api = CovidService()

    fun getTotalReports(date:String): Single<CovidModel> {
        return api.getTotalReports(date)
    }

}