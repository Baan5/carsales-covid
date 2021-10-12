package com.example.covidapiservicemodule.util

import java.text.SimpleDateFormat
import java.util.*

class DateFunctions {

    fun getDay(): Int{
        val parts = getDate().split("-")
        return parts[2].toInt()
    }
    fun getMonth(): Int{
        val parts = getDate().split("-")
        return parts[1].toInt() - 1
    }
    fun getYear(): Int{
        val parts = getDate().split("-")
        return parts[0].toInt()
    }

    fun getDate(): String{
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        var fecha = Calendar.getInstance()
        fecha.time = Date()
        return sdf.format(fecha.time)
    }

    fun getDateOneDayLess(): String{
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        var fecha = Calendar.getInstance()
        fecha.time = Date()
        fecha.add(Calendar.DAY_OF_MONTH, -1)
        return sdf.format(fecha.time)
    }

}