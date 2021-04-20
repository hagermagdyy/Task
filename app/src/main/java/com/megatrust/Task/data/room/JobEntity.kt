package com.megatrust.Task.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import kotlinx.coroutines.Job

@Entity(tableName = "JobEntity")
data class JobEntity (

    @PrimaryKey(autoGenerate = true)
    val JobID: Int,
    @ColumnInfo(name = "company_logo")
    val company_logo: String,
    @ColumnInfo(name = "how_to_apply")
    val how_to_apply: String,
    @ColumnInfo(name = "created_at")
    val created_at: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "company")
    val company: String,
    @ColumnInfo(name = "company_url")
    val company_url: String,
    @ColumnInfo(name = "location")
    val location: String,
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "url")
    val url: String,
    @ColumnInfo(name = "favorite")
    val favorite: Boolean) {

    companion object {
        const val TABLE_NAME = "jobData"
        const val TITLE = "title"
    }
}