package com.bangover.carsalescovid.di.component

import com.bangover.carsalescovid.data.CovidRepository
import com.bangover.carsalescovid.data.network.CovidService
import com.bangover.carsalescovid.di.module.CovidModule
import com.bangover.carsalescovid.domain.GetTotalReportsUseCase
import com.bangover.carsalescovid.ui.viewModel.CovidViewModel
import dagger.Component

@Component(modules = [CovidModule::class])
interface CovidComponent {

    fun inject(covidService: CovidService)
    fun inject(covidRepository: CovidRepository)
    fun inject(getTotalReportsUseCase: GetTotalReportsUseCase)
    fun inject(covidViewModel: CovidViewModel)

}