package com.example.moviesapp

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MoviesDAO {

    @Insert
    fun insert(movie : Movie)

    @Update
    fun update(movie : Movie)

    @Delete
    fun delete(movie : Movie)

    @Query("SELECT * FROM movies_table ORDER BY id ASC")
    fun getAllMovies(): LiveData<List<Movie>>
}