package com.example.moviesapp.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MoviesViewModel(
    var moviesRepository: MoviesRepository
): ViewModel() {
    companion object{
        var moviesList: List<Movie>? = null
    }
    fun insert(movie: Movie){
        moviesRepository.insert(movie)
    }
    fun update(movie: Movie){
        moviesRepository.update(movie)
    }
    fun delete(movie: Movie){
        moviesRepository.delete(movie)
    }
    fun getAllMovies(): LiveData<List<Movie>>?{
        moviesList = moviesRepository.getAllMovies()?.value
        return moviesRepository.getAllMovies()
    }
    fun getMovies(): MutableLiveData<List<Movie>>?{
        return moviesRepository.getMovies()
    }
    fun getAllMoviesfromService() {
        moviesRepository.getAllMoviesfromService()
    }
}