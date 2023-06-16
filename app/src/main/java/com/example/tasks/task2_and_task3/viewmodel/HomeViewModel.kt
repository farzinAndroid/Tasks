package com.example.tasks.task2_and_task3.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasks.task2_and_task3.data.model.AmazingItem
import com.example.tasks.task2_and_task3.data.remote.NetworkResult
import com.example.tasks.task2_and_task3.repository.HomeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: HomeRepo) : ViewModel() {


    /*private val _superMarketItems =
        flowOf<NetworkResult<List<AmazingItem>>>(NetworkResult.Loading())

    val superMarketItems = _superMarketItems*/


    val superMarketItems =
        MutableStateFlow<NetworkResult<List<AmazingItem>>>(NetworkResult.Loading())

    fun getDataFromServer() {
        viewModelScope.launch {
            launch {
                superMarketItems.emit(repo.getSuperMarketAmazingProducts())
            }
        }
    }

    val filteredSuperMarketItems = superMarketItems.map { networkResult ->
        when(networkResult){
            is NetworkResult.Success ->{
                val items = networkResult.data
                val filteredItems = items?.filter { it.discountPercent <= 10 }
                NetworkResult.Success("Success", filteredItems)
            }
            is NetworkResult.Error -> {
                NetworkResult.Error(message = "Error",data = null)
            }

            is NetworkResult.Loading -> {
                NetworkResult.Loading()
            }
        }

    }


    /*val filter = superMarketItems.map { list->
        list.data?.filter {
            it.discountPercent <= 10
        }
    }*/


}