package com.example.moviesapp

import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalStateException
import java.lang.RuntimeException

class MoviesViewModelFactory(
    var moviesRepository: MoviesRepository?
): ViewModelProvider.Factory {
    companion object{
        fun createFactory(activity: Activity): MoviesViewModelFactory {
            var context: Context? = null
            context = activity.applicationContext
            if(context == null){
                throw IllegalStateException("Not yet atached to application")
            }
            return MoviesViewModelFactory(
                MoviesRepository.getInstance(context)
            )
        }
    }
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        try{
            return modelClass.getConstructor(MoviesRepository::class.java).newInstance(moviesRepository)
        }catch (e: InstantiationException){
            throw RuntimeException("Cannot create an instance of $modelClass", e)
        }
    }
}