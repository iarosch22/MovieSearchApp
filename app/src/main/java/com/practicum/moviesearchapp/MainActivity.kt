package com.practicum.moviesearchapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val movieBaseUrl = "https://tv-api.com"

    private val retrofit = Retrofit.Builder()
        .baseUrl(movieBaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val movieService = retrofit.create(MovieApi::class.java)

    private lateinit var searchButton: Button
    private lateinit var searchInput: EditText
    private lateinit var placeholder: TextView
    private lateinit var moviesList: RecyclerView

    private val movies = ArrayList<Movie>()

    private val adapter = MovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchButton = findViewById(R.id.search_button)
        searchInput = findViewById(R.id.search_input)
        placeholder = findViewById(R.id.placeholder)
        moviesList = findViewById(R.id.rvMovie)

        adapter.movies = movies

        moviesList.adapter = adapter

        searchButton.setOnClickListener {
            if(searchInput.text.isNotEmpty()) {
                movieService.findMovie(searchInput.text.toString()).enqueue(object : Callback<MovieResponce> {
                    override fun onResponse(
                        call: Call<MovieResponce>,
                        response: Response<MovieResponce>
                    ) {
                        if (response.code() == 200) {
                            movies.clear()
                            if (response.body()?.results?.isNotEmpty() == true) {
                                movies.addAll(response.body()?.results!!)
                                adapter.notifyDataSetChanged()
                            }
                            if (movies.isEmpty()) {
                                showMessage(getString(R.string.nothing_found), "")
                            } else {
                                showMessage("", "")
                            }
                        } else {
                            showMessage(getString(R.string.something_went_wrong), response.code().toString())
                        }
                    }

                    override fun onFailure(call: Call<MovieResponce>, t: Throwable) {
                        showMessage(getString(R.string.something_went_wrong), t.message.toString())
                    }

                })
            }
        }
    }

    private fun showMessage(text: String, addtionalMessage: String) {
        if (text.isNotEmpty()) {
            placeholder.visibility = View.VISIBLE
            movies.clear()
            adapter.notifyDataSetChanged()
            placeholder.text = text

            if (addtionalMessage.isNotEmpty()) {
                Toast.makeText(applicationContext, addtionalMessage, Toast.LENGTH_LONG).show()
            }
        } else {
            placeholder.visibility = View.GONE
        }
    }
}