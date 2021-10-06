package com.bangover.carsalescovid.ui.viewModel

import com.example.covidapiservicemodule.util.DateFunctions
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CovidViewModelTest{

    private lateinit var viewModel: CovidViewModel

    @Before
    fun setUp() {
        viewModel = CovidViewModel()
    }

    @Test
    fun should_returnSuccess_when_dateSelectedIsOkFormat(){
        val dateSelected = viewModel.dateSelected(DateFunctions().getDay(),DateFunctions().getMonth(),DateFunctions().getYear())
        val regex = Regex("\\d{4}-\\d{2}-\\d{2}")
        assertTrue(regex.matches(dateSelected))
    }

    @Test
    fun should_returnError_when_dateSelectedIsFormatError(){
        val dateSelected = viewModel.dateSelected(DateFunctions().getDay(),DateFunctions().getYear(), DateFunctions().getMonth())
        val regex = Regex("\\d{4}-\\d{2}-\\d{2}")
        assertFalse(regex.matches(dateSelected))
    }

    @Test
    fun should_returnDate_when_dateSelectedIsValid(){
        val dateSelected = viewModel.convertDate(DateFunctions().getDate())
        val regex = Regex("\\d[0-9] \\w[a-z] \\w[a-z]+ \\w[a-z]+ \\d[0-9]+")
        assertTrue(regex.matches(dateSelected))
    }

}