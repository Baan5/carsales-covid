package com.bangover.carsalescovid.ui.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.bangover.carsalescovid.util.CovidViewModelFactory
import com.bangover.carsalescovid.util.TestCoroutineRule
import com.example.covidapiservicemodule.data.model.CovidModel
import com.example.covidapiservicemodule.util.DateFunctions
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CovidViewModelTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @get: Rule
    var testCoroutineRule = TestCoroutineRule()

    private lateinit var viewModel: CovidViewModel
    private lateinit var factory: CovidViewModelFactory

    @Mock
    lateinit var currentDateLiveDataObserver: Observer<String>
    @Mock
    lateinit var dataCovidLiveDataObserver: Observer<CovidModel?>
    @Mock
    lateinit var selectedDateLiveDataObserver: Observer<String>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = CovidViewModel().apply {
            currentDateLiveData.observeForever(currentDateLiveDataObserver)
            dataCovid.observeForever(dataCovidLiveDataObserver)
            selectedDate.observeForever(selectedDateLiveDataObserver)
        }
        factory = CovidViewModelFactory()
    }

    @Test
    fun should_returnSuccess_when_dateSelectedIsOkFormat(){
        viewModel.dateSelected(DateFunctions().getDay(),DateFunctions().getMonth(),DateFunctions().getYear())
        Mockito.verify(selectedDateLiveDataObserver).onChanged(DateFunctions().getDate())
    }

    @Test
    fun should_returnDate_when_dateSelectedIsValid(){
        val dateSelected = viewModel.convertDate(DateFunctions().getDate())
        val regex = Regex("\\d[0-9] \\w[a-z] \\w[a-z]+ \\w[a-z]+ \\d[0-9]+")
        assertTrue(regex.matches(dateSelected))
    }

    @Test
    fun should_returnDate_when_dateSelectedIsValid2(){
        viewModel.getCurrentDate()
        Mockito.verify(currentDateLiveDataObserver).onChanged(DateFunctions().getDateOneDayLess())
    }

}