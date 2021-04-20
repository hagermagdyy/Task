package com.megatrust.Task.ui.main.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.megatrust.Task.MyApplication
import com.megatrust.Task.data.api.RetrofitService
import com.megatrust.Task.data.model.model
import com.megatrust.Task.data.repository.MainRepository
import com.megatrust.Task.data.room.AppDatabase
import com.megatrust.Task.data.room.JobDAO
import com.megatrust.Task.data.room.JobEntity
import kotlinx.coroutines.*

class JobsViewModel constructor(private val repository: MainRepository)  : ViewModel()  {


    private val jobDao: JobDAO = AppDatabase.getAppDataBase(MyApplication.getContext())!!.JobDAO()
    val JobService = RetrofitService().getService()
    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val Jobs = MutableLiveData<List<model.ResponseItem>>()
    val jobsLoadError = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()
    var jobsList = MutableLiveData<List<JobEntity>>()

    init {
        //fetchJobs()
        insert(JobEntity(3, "hager", "hager", "tanta", "hager", "hager", "mostafa", "tanta", "female", "title", "url", false))
//        jobsList = jobDao.AllJobs
    }


    fun fetch(){

        CoroutineScope(Dispatchers.IO).launch {

            Log.i("Fetch_data_name", " " + jobDao.AllJobs().get(0).company)
            Log.i("Fetch_data", " " + jobsList.value?.size)

            jobsList.value = jobDao.AllJobs()
        }
    }

    fun refresh() {
       // fetchJobs()
    }

    fun insert( jobs: JobEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            jobDao.insertJob(jobs)
        }
    }

    fun update(jobs: JobEntity) {
        jobDao.UpdateFavorite(jobs)
    }

    fun deleteEntity(jobs: JobEntity) {
        jobDao.deleteJob(jobs)
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
