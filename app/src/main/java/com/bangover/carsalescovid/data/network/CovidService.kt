package com.bangover.carsalescovid.data.network

import android.util.Log
import com.bangover.carsalescovid.Core.RetrofitHelper
import com.bangover.carsalescovid.data.model.CovidModel
import com.bangover.carsalescovid.data.model.DataModel
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import java.lang.Exception
import javax.inject.Inject
import kotlin.math.log

class CovidService @Inject constructor(private val api: Retrofit){

    private val apiKey = "96afa298cbmsh913f910f914494cp110c39jsn01a32d68445e"

    suspend fun getTotalReports(date:String): CovidModel? {

        return withContext(Dispatchers.IO){
            try {
                val response = api.create(CovidApiClient::class.java).getTotalReports(apiKey, date)
                Log.d("TAG", response.body().toString())
                response.body()
            }catch (e: Exception){
                Log.d("TAG", "getTotalReports: " + e.message)
                null
            }

        }
    }
}