package com.bangover.carsalescovid

import android.app.Application
import au.com.carsales.basemodule.router.BaseModuleLifeCycleManager
import com.example.routercovidmodule.lifecycle.CovidApiServiceLifeCycle

class ModuleLifeCycleManager(application: Application): BaseModuleLifeCycleManager(application) {

    init {
        moduleLifeCycleList.add(CovidApiServiceLifeCycle(application))
    }

}