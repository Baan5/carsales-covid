package com.bangover.carsalescovid.ui.viewModel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangover.carsalescovid.data.model.CovidModel
import com.bangover.carsalescovid.data.model.DataModel
import com.bangover.carsalescovid.domain.GetTotalReportsUseCase
import javax.inject.Inject

class CovidViewModel @Inject constructor(val getTotalReportsUseCase: GetTotalReportsUseCase): ViewModel() {


    val dataCovid = MutableLiveData<CovidModel?>()
    val visibility = MutableLiveData<Boolean>()
    //val getTotalReportsUseCase = GetTotalReportsUseCase()

    @SuppressLint("CheckResult")
    fun getTotalReports(date:String){
        getTotalReportsUseCase(date)
            .subscribe(
                { covid ->
                    dataCovid.postValue(covid)
                    Log.d("getTag", "getTotalReports: ${covid.data.confirmed}")
                    visibility.postValue(false)
                },
                { error ->
                    Log.e("getTag", "getTotalReports: ${error.message}", )
                    visibility.postValue(false)
                    dataCovid.postValue(null)
                }
            )
    }

}