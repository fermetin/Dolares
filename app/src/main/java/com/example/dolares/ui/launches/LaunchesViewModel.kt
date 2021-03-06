package com.example.dolares.ui.launches

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dolares.data.local.model.launch.Launch
import com.example.dolares.data.repository.LaunchesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LaunchesViewModel(
    private val launchesRepository: LaunchesRepository
) : ViewModel() {

    val allLaunches: MutableLiveData<List<Launch>> = MutableLiveData()

    val loadingStatus: MutableLiveData<Boolean> = launchesRepository.loadingStatus
    val snackBarMessage: MutableLiveData<String> = launchesRepository.snackBarMessage
    val selectedLaunchId: MutableLiveData<String?> = MutableLiveData()

    fun getAllLaunches():LiveData<List<Launch>> = allLaunches

    init {
        viewModelScope.launch(Dispatchers.IO){
            launchesRepository.getAllLaunchesFlowFromDb()
                .collect {
                    if(it.isNullOrEmpty()) refreshData()
                    allLaunches.postValue(it)
                }
        }
    }

    fun refreshData() = viewModelScope.launch { launchesRepository.refreshData(true) }

    fun navigateSelectedLaunchDetailsScreen(launchId: String){
        selectedLaunchId.value = launchId
    }

    fun doneNavigateSelectedDetailsScreen(){
        selectedLaunchId.value = null
    }

}