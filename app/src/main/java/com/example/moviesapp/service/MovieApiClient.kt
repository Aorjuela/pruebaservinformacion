package com.example.moviesapp.service

import androidx.lifecycle.MutableLiveData
import com.example.moviesapp.model.Movie
import com.example.moviesapp.model.ServiceResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import java.lang.Exception
import java.util.concurrent.*

class MovieApiClient {
    var retrieveMoviesRunnable: RetrieveMoviesRunnable? = null
    companion object{
        var mMovies: MutableLiveData<List<Movie>>? = MutableLiveData()
        var INSTANCE: MovieApiClient? = null
        fun getInstance(): MovieApiClient?{
            if(INSTANCE == null){
                synchronized(MovieApiClient::class.java){
                    if(INSTANCE == null) {
                        INSTANCE = MovieApiClient()
                    }
                }
            }
            return INSTANCE
        }
    }

    fun getMovies(): MutableLiveData<List<Movie>>?{
        return mMovies;
    }

    fun searchMoviesApi(){
        if(retrieveMoviesRunnable != null){
            retrieveMoviesRunnable = null
        }
        retrieveMoviesRunnable = RetrieveMoviesRunnable()
        val executor = Executors.newScheduledThreadPool(3)
        val myHandler = executor.submit(retrieveMoviesRunnable)
        executor.schedule(Runnable { myHandler.cancel(true) },4000000, TimeUnit.MILLISECONDS)
    }

    class RetrieveMoviesRunnable: Runnable{
        var cancelRequest: Boolean = false
        override fun run() {
            try{
                val response: Response<ServiceResponse>? = getMovies()?.execute()
                if(cancelRequest){
                    return
                }
                if(response?.code() == 200){
                    val serviceResponse: ServiceResponse? = response.body()
                    val moviesList: List<Movie>? = serviceResponse?.results
                    mMovies?.postValue(moviesList)
                    cancelRequest()
                }else{
                    mMovies?.postValue(null)
                }
            }catch (exeption: Exception){
                mMovies?.postValue(null)
            }
        }
        fun getMovies(): Call<ServiceResponse>?{
            val retrofit: Retrofit? = RetrofitClientService.getRetrofitInstance()
            val apiService: ApiService? = retrofit?.create(ApiService::class.java)
            return apiService?.getMovies(3,1, RetrofitClientService.API_KEY)
        }
        fun cancelRequest(){
            cancelRequest = true
        }
    }
}

