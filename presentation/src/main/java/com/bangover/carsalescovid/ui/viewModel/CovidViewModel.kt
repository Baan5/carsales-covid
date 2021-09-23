package com.bangover.carsalescovid.ui.viewModel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import au.com.carsales.basemodule.context
import com.bangover.carsalescovid.getCovidApiReceivedService
import com.example.covidapiservicemodule.data.model.CovidModel
import kotlinx.coroutines.launch

class CovidViewModel: ViewModel(){


    val dataCovid = MutableLiveData<CovidModel?>()
    val visibility = MutableLiveData<Boolean>()

    @SuppressLint("CheckResult")
    fun getTotalReports(date:String){

        viewModelScope.launch {
            val result = context!!.getCovidApiReceivedService()?.getData(date)
            Log.d("getTag", "getTotalReports from viewmodel: ")
            if (result != null){
                dataCovid.postValue(result)
                visibility.postValue(false)
            }else {
                visibility.postValue(false)
                dataCovid.postValue(null)
            }
        }
    }

}