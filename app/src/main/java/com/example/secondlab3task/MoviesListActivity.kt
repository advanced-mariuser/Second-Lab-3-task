package com.example.secondlab3task

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.bloco.faker.Faker

class MoviesListActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_list)

        val movies = generateFakeMovies(20)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.addItemDecoration(Decoration(resources))
        recyclerView.adapter = MoviesAdapter(movies) { movie ->
            val intent = Intent(this, MovieActivity::class.java)
            intent.putExtra("movie", movie)
            startActivity(intent)
        }
    }

    fun generateFakeMovies(count: Int): List<Movie>
    {
        val faker = Faker()
        val movies = mutableListOf<Movie>()

        for (i in 1..count)
        {
            val movie = Movie(
                title = faker.book.title(),
                description = faker.lorem.paragraph(),
                rating = (1..5).random().toDouble(),
                imageUrl = "https://loremflickr.com/" + (480..1920).random() + "/" + (300..1080).random()
            )
            movies.add(movie)
        }

        return movies
    }
}