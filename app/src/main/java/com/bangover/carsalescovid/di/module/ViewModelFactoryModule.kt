package com.bangover.carsalescovid.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangover.carsalescovid.di.ViewModelKey
import com.bangover.carsalescovid.ui.viewModel.CovidViewModel
import com.bangover.carsalescovid.ui.viewModel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CovidViewModel::class)
    abstract fun splashViewModel(viewModel: CovidViewModel): ViewModel
}