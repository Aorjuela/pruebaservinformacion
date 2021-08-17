package com.example.moviesapp.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClientService {
    companion object{
        var retrofit: Retrofit? = null
        val BASE_URL: String = "https://api.themoviedb.org/"
        val API_KEY: String = "793473671fd6aad0194c3fd7c3779d17"
        fun getRetrofitInstance(): Retrofit?{
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit
        }
    }
}