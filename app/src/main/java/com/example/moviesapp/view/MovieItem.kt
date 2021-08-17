package com.example.moviesapp.view

import android.widget.TextView
import com.example.moviesapp.R
import com.example.moviesapp.model.Movie
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.dialog_fragment.view.*
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieItem(
        val movie: Movie)
    : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.text_view_movie_name.text = movie.title
    }

    override fun getLayout(): Int = R.layout.movie_item
}