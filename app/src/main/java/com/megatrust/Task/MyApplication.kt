package com.megatrust.Task

import android.app.Application
import android.content.Context

class MyApplication : Application() {

    companion object{

        lateinit var  instance: Context
        fun getContext():Context= instance
    }


    override fun onCreate() {
        super.onCreate()

        instance=applicationContext
    }
}