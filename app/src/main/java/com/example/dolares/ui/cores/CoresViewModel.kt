package com.example.dolares.ui.cores

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dolares.data.local.model.Core
import com.example.dolares.data.repository.CoresRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CoresViewModel(
    private val coresRepository: CoresRepository
) : ViewModel() {

    val allCores:MutableLiveData<List<Core>> = MutableLiveData<List<Core>>()

    init {
        refreshCoreData()
        viewModelScope.launch(Dispatchers.IO){
            coresRepository.getAllCoresFlowFromDb()
                .collect { allCores.postValue(it) }
        }
    }

    fun getAllCores():LiveData<List<Core>> = allCores

    fun refreshCoreData() = viewModelScope.launch { coresRepository.fetchAllDataSaveToDb() }

}