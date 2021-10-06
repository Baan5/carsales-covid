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
        return parts[1].toInt()
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

}