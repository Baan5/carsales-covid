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

        covidViewModel.getCurrentDate()
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

                binding.tvFecha.text = covidViewModel.convertDate(data.data.date)
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

    private fun showDatePicker() {
        try {
            datePicker.show(supportFragmentManager, "datePicker")
        }catch (e: Exception){
            Log.e("getTag", "showDatePicker: ${e.message}")
        }

    }

    private fun onDateSelected(day: Int, month: Int, year: Int) {
        val date = covidViewModel.dateSelected(day, month, year)
        covidViewModel.visibility.postValue(true)
        covidViewModel.selectedDate.postValue(date)
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