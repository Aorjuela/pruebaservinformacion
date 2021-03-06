package com.example.moviesapp.service

import com.example.moviesapp.model.ServiceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("4/list/{list_num}")
    fun getMovies(
        @Path("list_num") listNum: Int,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String): Call<ServiceResponse>
}