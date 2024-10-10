package com.example.secondlab3task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MoviesAdapter(
    private val movies: List<Movie>,
    private val onClick: (Movie) -> Unit
) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>()
{
    class MovieViewHolder(view: View, val onClick: (Movie) -> Unit) : RecyclerView.ViewHolder(view)
    {
        val movieTitle: TextView = view.findViewById(R.id.movieTitle)
        val movieRating: TextView = view.findViewById(R.id.movieRating)
        val movieImage: ImageView = view.findViewById(R.id.movieImage)
        val movieDescription: TextView = view.findViewById(R.id.movieDescription)
        private var currentMovie: Movie? = null

        init
        {
            view.setOnClickListener {
                currentMovie?.let {
                    onClick(it)
                }
            }
        }

        fun bind(movie: Movie)
        {
            currentMovie = movie
            movieTitle.text = movie.title
            movieRating.text = movie.rating.toString()
            movieDescription.text = movie.description

            Glide.with(itemView.context)
                .load(movie.imageUrl)
                .into(movieImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int)
    {
        holder.bind(movies[position])
    }

    override fun getItemCount() = movies.size
}