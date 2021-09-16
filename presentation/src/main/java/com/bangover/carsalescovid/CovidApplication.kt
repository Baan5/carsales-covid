package com.bangover.carsalescovid

import android.content.Context
import au.com.carsales.basemodule.BaseModuleApplication
import au.com.carsales.basemodule.router.BaseModuleLifeCycleManager
import com.bangover.carsalescovid.di.component.AppComponent
import com.bangover.carsalescovid.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class CovidApplication: DaggerApplication() {

    /*companion object{
        var applicationComponent: AppComponent? = null
    }

    override fun initLifeCycleManager(): BaseModuleLifeCycleManager {
        return ModuleLifeCycleManager(this)
    }

    *//*override fun onCreate() {
        super.onCreate()
        appComponent().inject(this)
    }

    fun Context.appComponent(): AppComponent {
        //return build
    }

    private fun buildDagger(context: Context): AppComponent {
        if (applicationComponent == null){
            //applicationComponent = DaggerAppComponent.builder()
        }
    }*//*

}*/




    lateinit var appComponent: AppComponent

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        appComponent = DaggerAppComponent.builder().application(this).build()
        return appComponent
    }
}
