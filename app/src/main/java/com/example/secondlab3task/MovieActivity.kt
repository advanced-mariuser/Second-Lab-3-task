package com.example.secondlab3task

import android.os.Build
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class MovieActivity : AppCompatActivity()
{
    private lateinit var movieTitle: TextView
    private lateinit var movieDescription: TextView
    private lateinit var movieRating: TextView
    private lateinit var movieImage: ImageView
    private lateinit var backButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        movieTitle = findViewById(R.id.movieTitle)
        movieDescription = findViewById(R.id.movieDescription)
        movieRating = findViewById(R.id.movieRating)
        movieImage = findViewById(R.id.movieImage)
        backButton = findViewById(R.id.backButton)

        val movie: Movie? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("movie", Movie::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra("movie") as? Movie
        }

        movie?.let {
            movieTitle.text = it.title
            movieDescription.text = it.description
            movieRating.text = it.rating.toString()

            Glide.with(this)
                .load(it.imageUrl)
                .into(movieImage)
        }

        backButton.setOnClickListener {
            finish()
        }
    }
}