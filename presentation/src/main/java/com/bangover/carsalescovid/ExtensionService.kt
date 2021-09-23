package com.bangover.carsalescovid

import android.content.Context
import au.com.carsales.basemodule.router.Router
import com.example.covidapiservicemodule.service.ICovidApiReceivedService

fun Context.getCovidApiReceivedService(): ICovidApiReceivedService? {
    if (CovidApplication.covidApiReceivedService == null){
        CovidApplication.covidApiReceivedService =
            Router.getService(ICovidApiReceivedService::class.java)
    }
    return CovidApplication.covidApiReceivedService
}

fun String.replaceCommaToPoint():String{
    return this.replace(",", ".")
}