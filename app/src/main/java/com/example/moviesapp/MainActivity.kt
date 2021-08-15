package com.example.moviesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit: Retrofit? = RetrofitClientService.getRetrofitInstance()

        val apiService: ApiService? = retrofit?.create(ApiService::class.java)

        apiService?.getMovies(3,1,RetrofitClientService.API_KEY)?.enqueue(object : Callback<com.example.moviesapp.Response>{
            override fun onResponse(call: Call<com.example.moviesapp.Response>, response: Response<com.example.moviesapp.Response>) {
                val response: com.example.moviesapp.Response? = response.body()
                val algo: String = "";
            }
            override fun onFailure(call: Call<com.example.moviesapp.Response>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}