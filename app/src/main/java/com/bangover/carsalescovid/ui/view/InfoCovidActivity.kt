package com.bangover.carsalescovid.ui.view

import android.annotation.SuppressLint
import android.icu.util.LocaleData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.bangover.carsalescovid.Core.DatePickerFragment
import com.bangover.carsalescovid.databinding.ActivityInfoCovidBinding
import com.bangover.carsalescovid.ui.viewModel.CovidViewModel
import com.bangover.carsalescovid.ui.viewModel.ViewModelFactory
import dagger.android.AndroidInjection
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class InfoCovidActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInfoCovidBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val covidViewModel: CovidViewModel by viewModels{viewModelFactory}

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityInfoCovidBinding.inflate(layoutInflater)
        setContentView(binding.root)

        covidViewModel.visibility.postValue(true)
        covidViewModel.getTotalReports(currentDate())

        binding.btnDate.setOnClickListener{showDatePicker()}

        covidViewModel.dataCovid.observe(this, {data ->
            if (data != null){
                val format = DecimalFormat("#,###")
                var confirmed = format.format(data.data.confirmed)
                var death = format.format(data.data.deaths)

                binding.tvFecha.text = data.data.date
                binding.tvConfirmados.text = confirmed.replace(",", ".")
                binding.tvFallecidos.text = death.replace(",", ".")
            }else{
                Toast.makeText(this, "Fecha seleccionada sin resultados", Toast.LENGTH_LONG).show()
            }

        })

        covidViewModel.visibility.observe(this, {
            binding.progressCircular.isVisible = it
            binding.lyData.isVisible = !it
        })

    }

    private fun showDatePicker() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
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
        covidViewModel.getTotalReports(String.format("%s-%s-%s", year, mes, dia))
    }

    @SuppressLint("SimpleDateFormat")
    private fun currentDate():String{
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        var fecha = Calendar.getInstance()
        fecha.time = Date()
        fecha.add(Calendar.DAY_OF_MONTH, -1)
        val currentDate = sdf.format(fecha.time)
        Log.d("getTag", "currentDate: $currentDate")
        return currentDate
    }
}