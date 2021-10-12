package com.bangover.carsalescovid.util

import com.example.covidapiservicemodule.data.model.CovidModel
import com.example.covidapiservicemodule.data.model.DataModel

class CovidViewModelFactory {

    fun getCovidData(): CovidModel {
        return CovidModel(
            DataModel(
                active = 0,
                active_diff = 0,
                confirmed = 0,
                confirmed_diff = 0,
                date = "",
                deaths = 0,
                deaths_diff = 0,
                fatality_rate = 0.0,
                last_update = "",
                recovered = 0,
                recovered_diff = 0

            )
        )
    }

}