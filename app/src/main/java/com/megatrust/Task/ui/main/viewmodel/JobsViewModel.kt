package com.megatrust.Task.ui.main.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.megatrust.Task.data.api.ApiClient
import com.megatrust.Task.data.api.RetrofitService
import com.megatrust.Task.data.model.model
import com.megatrust.Task.data.repository.MainRepository
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JobsViewModel constructor(private val repository: MainRepository)  : ViewModel()  {

    val JobService = RetrofitService().getService()
    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val Jobs = MutableLiveData<List<model.ResponseItem>>()
    val jobsLoadError = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()

    init {
        fetchJobs()
    }

    fun refresh() {
        fetchJobs()
    }

     private fun fetchJobs() {
         Log.d("Fetch_Jobs", "fetchJobs: " )

         loading.value = true
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = JobService.getJobs()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Jobs.value = response.body()
                    jobsLoadError.value = null
                    Log.d("Fetch_Jobs", "fetchJobs: Success" + response.body()?.get(0))
                    Log.d("Fetch_Jobs", "fetchJobs: " + response.errorBody())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                    Log.d("Fetch_Jobs", "fetchJobs: Error" + response.body() )

                }
            }
        }
        jobsLoadError.value = ""
        loading.value = false
    }

    private fun onError(message: String) {
        jobsLoadError.value = message
        loading.value = false
    }
    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}
