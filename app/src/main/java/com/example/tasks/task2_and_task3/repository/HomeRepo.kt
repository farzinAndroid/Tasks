package com.example.tasks.task2_and_task3.repository

import com.example.tasks.task2_and_task3.data.model.AmazingItem
import com.example.tasks.task2_and_task3.data.model.ResponseResult
import com.example.tasks.task2_and_task3.data.remote.BaseApiResponse
import com.example.tasks.task2_and_task3.data.remote.HomeApiInterface
import com.example.tasks.task2_and_task3.data.remote.NetworkResult
import retrofit2.Response
import javax.inject.Inject

class HomeRepo @Inject constructor(private val api: HomeApiInterface) : BaseApiResponse() {


    suspend fun getSuperMarketAmazingProducts(): NetworkResult<List<AmazingItem>> =
       safeApiCall {
           api.getSuperMarketAmazingProducts()
       }

}