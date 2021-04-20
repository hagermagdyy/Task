package com.megatrust.Task.data.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.megatrust.Task.data.model.model

@Dao
interface JobDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertJob(Job: JobEntity)

    @Update
    fun UpdateFavorite(Job: JobEntity)

    @Delete
    fun deleteJob(Job: JobEntity)

    @Query("SELECT * FROM JobEntity")
    suspend fun AllJobs():List<JobEntity>

}