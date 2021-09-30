package com.bangover.carsalescovid.ui.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.bangover.carsalescovid.Core.DatePickerFragment
import com.bangover.carsalescovid.databinding.ActivityInfoCovidBinding
import com.bangover.carsalescovid.replaceCommaToPoint
import com.bangover.carsalescovid.ui.viewModel.CovidViewModel
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

class InfoCovidActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInfoCovidBinding
    val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }

    private val covidViewModel: CovidViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoCovidBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currentDate()
        covidViewModel.visibility.postValue(true)
        covidViewModel.currentDate.observe(this, {
            covidViewModel.getTotalReports()
        })
        covidViewModel.selectedDate.observe(this, {
            covidViewModel.getTotalReports()
        })

        binding.btnDate.setOnClickListener{showDatePicker()}

        covidViewModel.dataCovid.observe(this, {data ->
            if (data != null){
                val format = DecimalFormat("#,###")
                var confirmed = format.format(data.data.confirmed)
                var death = format.format(data.data.deaths)

                binding.tvFecha.text = convertDate(data.data.date)
                binding.tvConfirmados.text = confirmed.replaceCommaToPoint()
                binding.tvFallecidos.text = death.replaceCommaToPoint()
            }else{
                Toast.makeText(this, "Fecha seleccionada sin resultados", Toast.LENGTH_LONG).show()
            }

        })

        covidViewModel.visibility.observe(this, {
            binding.progressCircular.isVisible = it
            binding.lyData.isVisible = !it
        })

    }

    private fun convertDate(fecha: String): String{

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
        var mNameMonth:Int = 0

        if (parts[1].toInt() < 10) mNameMonth = parts[1].toInt()

        return String.format("%s de %s del %s", parts[2], mMonth[mNameMonth-1], parts[0])

    }

    private fun showDatePicker() {
        try {
            datePicker.show(supportFragmentManager, "datePicker")
        }catch (e: Exception){
            Log.e("getTag", "showDatePicker: ${e.message}")
        }

    }

    private fun onDateSelected(day: Int, month: Int, year: Int) {
        Log.d("getTag", "onDateSelected: " + String.format("%s-%s-%s", year, month, day))
        var mes = ""
        var dia = ""
        if (month + 1 < 10){
            mes = "0" + (month + 1)
        }else mes = (month + 1).toString()

        if (day < 10){
            dia = "0" + day
        }else dia = day.toString()

        Log.d("getTag", "onDateSelected: " + String.format("%s-%s-%s", year, mes, dia))
        covidViewModel.visibility.postValue(true)
        covidViewModel.selectedDate.postValue(String.format("%s-%s-%s", year, mes, dia))
    }

    @SuppressLint("SimpleDateFormat")
    private fun currentDate(){
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        var fecha = Calendar.getInstance()
        fecha.time = Date()
        fecha.add(Calendar.DAY_OF_MONTH, -1)
        val currentDate = sdf.format(fecha.time)
        covidViewModel.currentDate.postValue(currentDate)
    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            datePicker.dismiss()
        }catch (e: Exception){
            Log.e("getTag", "onDestroy: ${e.message}")
        }
    }

    override fun onPause() {
        super.onPause()
        try {
            datePicker.dismiss()
        }catch (e: Exception){
            Log.e("getTag", "onPause: ${e.message}")
        }

    }
}