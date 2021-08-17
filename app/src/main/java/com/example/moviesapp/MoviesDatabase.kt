package com.example.moviesapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Movie::class], version = 3, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun moviesDAO(): MoviesDAO

    companion object {
        var INSTANCE: MoviesDatabase? = null

        fun getInstance(context: Context): MoviesDatabase? {
            if (INSTANCE == null){
                synchronized(MoviesDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, MoviesDatabase::class.java, "myDBExamples")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }
}