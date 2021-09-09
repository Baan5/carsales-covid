package com.bangover.carsalescovid.di.module

import com.bangover.carsalescovid.ui.view.InfoCovidActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract fun provideInfoCovidActivity(): InfoCovidActivity
}