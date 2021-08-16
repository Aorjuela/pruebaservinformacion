package com.example.moviesapp

import android.widget.TextView
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder

class MovieItem(
    private val movie: Movie)
    : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        val textViewMovieName: TextView = viewHolder.itemView.findViewById(R.id.text_view_movie_name)
        textViewMovieName.text = movie.title
    }

    override fun getLayout(): Int = R.layout.movie_item
}