package com.megatrust.Task.ui.main.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.megatrust.Task.data.model.model
import com.megatrust.Task.data.repository.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JobsViewModel constructor(private val repository: MainRepository)  : ViewModel()  {

    val JobList = MutableLiveData<List<model.ResponseItem>>()
    val errorMessage = MutableLiveData<String>()


    init {
        getAllJobs()
    }

    fun getAllJobs() {
        Log.d("getAllMovies", "getAllMovies: ")
        val response = repository.getAllJobs()
        response.enqueue(object : Callback<List<model.ResponseItem>> {
            override fun onResponse(call: Call<List<model.ResponseItem>>, response: Response<List<model.ResponseItem>>) {
                JobList.postValue(response.body())
                Log.d("Response_success", "eldata weslet");
            }

            override fun onFailure(call: Call<List<model.ResponseItem>>, t: Throwable) {
                errorMessage.postValue(t.message)
                Log.d("Response_fail","eldata weslet");

            }
        })
    }
}