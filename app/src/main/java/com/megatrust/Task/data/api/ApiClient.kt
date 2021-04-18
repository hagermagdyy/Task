package com.megatrust.Task.data.api

import com.megatrust.Task.data.model.model
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiClient {

    @GET("positions.json?description=api")
    fun getJobs(): Call<List<model.ResponseItem>>

    companion object {

        var retrofitService: ApiClient? = null

        fun getInstance() : ApiClient {

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://jobs.github.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(ApiClient::class.java)
            }
            return retrofitService!!
        }
    }
}