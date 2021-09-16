package com.bangover.carsalescovid

import android.app.Application
import au.com.carsales.basemodule.router.BaseModuleLifeCycleManager
import com.example.routercovidmodule.lifecycle.GlobalCovidRouterServiceLifeCycle

class ModuleLifeCycleManager(application: Application): BaseModuleLifeCycleManager(application) {

    init {
        moduleLifeCycleList.add(GlobalCovidRouterServiceLifeCycle(application))
    }

}