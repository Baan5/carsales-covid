package com.bangover.carsalescovid.di.component

import android.app.Application
import au.com.carsales.basemodule.dependency.component.BaseApplicationComponent
import com.bangover.carsalescovid.CovidApplication
import com.bangover.carsalescovid.di.module.ActivityBindingModule
import com.bangover.carsalescovid.di.module.ApiModule
import com.bangover.carsalescovid.di.module.ViewModelFactoryModule
import com.bangover.carsalescovid.ui.view.InfoCovidActivity
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ApiModule::class,
        AndroidSupportInjectionModule::class,
        AndroidInjectionModule::class,
        ViewModelFactoryModule::class,
        ActivityBindingModule::class]
)
interface AppComponent: AndroidInjector<CovidApplication> {

//    fun inject(infoCovidActivity: InfoCovidActivity)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}