package com.example.moviesapp.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.R
import com.example.moviesapp.model.MoviesViewModel
import com.example.moviesapp.model.MoviesViewModelFactory
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.OnItemClickListener
import com.xwray.groupie.ViewHolder

private var moviesViewModel: MoviesViewModel? = null
private var moviesRecyclerView: RecyclerView? = null

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
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

        adapter.setOnItemClickListener { item, view ->
            val movieItem = item as MovieItem
            val movie = movieItem.movie
            val customDialogFragment = CustomDialogFragment.newInstance(movie.title,movie.overview)
            customDialogFragment.show(supportFragmentManager,"customFragment")
        }
    }
}

