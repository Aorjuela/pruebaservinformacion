package com.example.moviesapp

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MoviesRepository(
    private var moviesDAO: MoviesDAO?,
    private var executor: ExecutorService
) {
    private val movieApiClient = MovieApiClient.getInstance()
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
        return moviesDAO?.getAllMovies()
    }

    fun getAllMoviesfromService() {
        movieApiClient?.searchMoviesApi()
    }

    fun getMovies(): MutableLiveData<List<Movie>>?{
        return movieApiClient?.getMovies()
    }
}