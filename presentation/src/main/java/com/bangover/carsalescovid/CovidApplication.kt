package com.bangover.carsalescovid

import au.com.carsales.basemodule.BaseModuleApplication
import au.com.carsales.basemodule.router.BaseModuleLifeCycleManager
import com.example.covidapiservicemodule.service.ICovidApiReceivedService

class CovidApplication: BaseModuleApplication() {

    companion object {
        internal var covidApiReceivedService: ICovidApiReceivedService? = null
    }

    override fun initLifeCycleManager(): BaseModuleLifeCycleManager {
        return ModuleLifeCycleManager(this)
    }
}
