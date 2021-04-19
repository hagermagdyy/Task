package com.megatrust.Task.data.api

import com.megatrust.Task.data.model.model
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiClient {

    @GET("positions.json?description=api")
    suspend fun getJobs(): Response<List<model.ResponseItem>>
}