package com.bangover.carsalescovid.domain

import com.bangover.carsalescovid.data.CovidRepository
import com.bangover.carsalescovid.data.model.CovidModel
import com.bangover.carsalescovid.data.model.DataModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class GetTotalReportsUseCase @Inject constructor(val repository: CovidRepository){

    //private val repositoy = CovidRepository()

    operator fun invoke(date:String): Single<CovidModel> = repository.getTotalReports(date)
        .observeOn(AndroidSchedulers.mainThread())


}