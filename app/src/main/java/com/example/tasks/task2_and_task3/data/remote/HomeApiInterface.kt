package com.example.tasks.task2_and_task3.data.remote

import com.example.tasks.task2_and_task3.data.model.AmazingItem
import com.example.tasks.task2_and_task3.data.model.ResponseResult
import retrofit2.Response
import retrofit2.http.GET

interface HomeApiInterface {

    @GET("v1/getSuperMarketAmazingProducts")
    suspend fun getSuperMarketAmazingProducts():
            Response<ResponseResult<List<AmazingItem>>>

}