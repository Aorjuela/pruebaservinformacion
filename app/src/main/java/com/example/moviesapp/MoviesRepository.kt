package com.example.moviesapp

import android.content.Context
import androidx.lifecycle.LiveData
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MoviesRepository(
    private var moviesDAO: MoviesDAO?,
    private var executor: ExecutorService
) {
    companion object {
        var INSTANCE: MoviesRepository? = null
        fun getInstance(context: Context): MoviesRepository? {
            if (INSTANCE == null){
                synchronized(MoviesRepository::class){
                    if (INSTANCE == null){
                        var moviesDatabase: MoviesDatabase? = MoviesDatabase.getInstance(context)
                        INSTANCE =
                            MoviesRepository(
                                moviesDatabase?.moviesDAO(),
                                Executors.newSingleThreadExecutor()
                            )
                    }
                }
            }
            return INSTANCE
        }}

    fun insert(movie: Movie){
        executor.execute(Runnable { moviesDAO?.insert(movie) })
    }

    fun update(movie: Movie){
        executor.execute(Runnable { moviesDAO?.insert(movie) })
    }

    fun delete(movie: Movie){
        executor.execute(Runnable { moviesDAO?.insert(movie) })
    }

    fun getAllMovies(): LiveData<List<Movie>>?{
        return moviesDAO?.getAllMovies();
    }
}