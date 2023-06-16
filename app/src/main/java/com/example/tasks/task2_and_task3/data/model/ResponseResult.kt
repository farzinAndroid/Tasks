package com.example.tasks.task2_and_task3.data.model

data class ResponseResult<T>(
    val message : String ,
    val data : T ,
    val success: Boolean
)