package com.example.moviesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

private var moviesViewModel: MoviesViewModel? = null
private var moviesRecyclerView: RecyclerView? = null

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit: Retrofit? = RetrofitClientService.getRetrofitInstance()
        val apiService: ApiService? = retrofit?.create(ApiService::class.java)
        val adapter = GroupAdapter<ViewHolder>()

        var moviesViewModelFactory: MoviesViewModelFactory =
            MoviesViewModelFactory.createFactory(
                this
            )

        moviesViewModel = ViewModelProviders.of(this, moviesViewModelFactory).get(MoviesViewModel::class.java)

        moviesRecyclerView = findViewById(R.id.recycler_view_movies);
        moviesRecyclerView?.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        apiService?.getMovies(3,1,RetrofitClientService.API_KEY)?.enqueue(object : Callback<ServiceResponse>{

            override fun onResponse(call: Call<ServiceResponse>, response: Response<ServiceResponse>
            ) {
                val serviceResponse: ServiceResponse? = response.body()
                val moviesList: List<Movie>? = serviceResponse?.results
                if (moviesList != null) {
                    for(movie in moviesList) {
                        moviesViewModel?.insert(movie)
                    }
                }
            }

            override fun onFailure(call: Call<ServiceResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

        moviesViewModel?.getAllMovies()?.observe(this, Observer { Movies ->
            Movies.forEach { adapter.add(MovieItem(it)) }
            moviesRecyclerView?.adapter = adapter
        })
    }
}