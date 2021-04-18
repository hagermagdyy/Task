package com.megatrust.Task.data.repository

import com.megatrust.Task.data.api.ApiClient

class MainRepository constructor(private val retrofitService: ApiClient) {

    fun getAllJobs() = retrofitService.getJobs()
}