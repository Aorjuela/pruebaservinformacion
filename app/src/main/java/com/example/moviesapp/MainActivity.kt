package com.example.moviesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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

        val adapter = GroupAdapter<ViewHolder>()

        var moviesViewModelFactory: MoviesViewModelFactory =
            MoviesViewModelFactory.createFactory(
                this
            )

        moviesViewModel = ViewModelProviders.of(this, moviesViewModelFactory).get(MoviesViewModel::class.java)

        moviesRecyclerView = findViewById(R.id.recycler_view_movies);
        moviesRecyclerView?.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        moviesViewModel?.getAllMovies()?.observe(this, Observer {
            if(it.isNotEmpty()){
                for(movie in it){
                    adapter.add(MovieItem(movie))
                }
                moviesRecyclerView?.adapter = adapter
            }else{
                moviesViewModel?.getAllMoviesfromService()
                moviesViewModel?.getMovies()?.observe(this, Observer {
                    if(it != null){
                        for(movie in it){
                            moviesViewModel?.insert(movie)
                        }
                    }else{
                        Toast.makeText(this, "No hay datos", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        })
    }
}