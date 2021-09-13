package com.bangover.carsalescovid

import com.bangover.carsalescovid.di.component.AppComponent
import com.bangover.carsalescovid.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class ApplicationClass: DaggerApplication() {
    lateinit var appComponent: AppComponent

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        appComponent = DaggerAppComponent.builder().application(this).build()
        return appComponent
    }
}