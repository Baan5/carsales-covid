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
import java.text.SimpleDateFormat
import java.util.*

class CovidViewModel: ViewModel(){


    val dataCovid: MutableLiveData<CovidModel?> = MutableLiveData()
    val visibility = MutableLiveData<Boolean>()
    val currentDateLiveData: MutableLiveData<String> = MutableLiveData()
    val selectedDate = MutableLiveData<String>()

    @SuppressLint("CheckResult")
    fun getTotalReports(){

        viewModelScope.launch {
            var date:String
            if (selectedDate.value != null) date = selectedDate.value!!
            else date = currentDateLiveData.value!!

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

    fun dateSelected(day: Int, month: Int, year: Int): String{
        var mMonth = ""
        var mDay = ""
        if (month + 1 < 10){
            mMonth = "0" + (month + 1)
        }else mMonth = (month + 1).toString()

        if (day < 10){
            mDay = "0$day"
        }else mDay = day.toString()

        return String.format("%s-%s-%s", year, mMonth, mDay)
    }

    fun convertDate(fecha: String): String{

        var mMonth = arrayOf(
            "Enero",
            "Febrero",
            "Marzo",
            "Abril",
            "Mayo",
            "Junio",
            "Julio",
            "Agosto",
            "Septiembre",
            "Obtubre",
            "Noviembre",
            "Diciembre"
        )

        var parts = fecha.split("-")
        var mNameMonth:Int = parts[1].toInt()

        return String.format("%s de %s del %s", parts[2], mMonth[mNameMonth-1], parts[0])

    }

    @SuppressLint("SimpleDateFormat")
    fun getCurrentDate(){
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        var fecha = Calendar.getInstance()
        fecha.time = Date()
        fecha.add(Calendar.DAY_OF_MONTH, -1)
        val date = sdf.format(fecha.time)
        currentDateLiveData.postValue(date)
    }

}