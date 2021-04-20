package com.megatrust.Task.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.megatrust.Task.MyApplication

@Database(entities = [JobEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun JobDAO(): JobDAO

    companion object {
        var INSTANCE: AppDatabase? = null

        fun getAppDataBase(context: Context): AppDatabase? {
            if (INSTANCE == null){
                synchronized(AppDatabase::class){
                    val instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "myDB").fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
            }
            return INSTANCE
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }
}



//public abstract class Roomdata :RoomDatabase(){
//
//    abstract fun roomDao(): RoomDao
//
//    companion object {
//        // Singleton prevents multiple instances of database opening at the
//        // same time.
//        // @Volatile
//        private var INSTANCE: Roomdata? = null
//
//        fun getDatabase(context: Context): Roomdata {
//            // if the INSTANCE is not null, then return it,
//            // if it is, then create the database
//            return INSTANCE
//                ?: synchronized(this) {
//                    val instance = Room.databaseBuilder(context.applicationContext, Roomdata::class.java, "weather table").fallbackToDestructiveMigration().build()
//                    INSTANCE = instance
//                    // return instance
//                    instance
//                }
//        }
//    }
//}