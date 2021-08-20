package com.bangover.carsalescovid.domain

import com.bangover.carsalescovid.data.CovidRepository
import com.bangover.carsalescovid.data.model.CovidModel
import com.bangover.carsalescovid.data.model.DataModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers

class GetTotalReportsUseCase {

    private val repositoy = CovidRepository()

    operator fun invoke(date:String): Single<CovidModel> = repositoy.getTotalReports(date)
        .observeOn(AndroidSchedulers.mainThread())


}