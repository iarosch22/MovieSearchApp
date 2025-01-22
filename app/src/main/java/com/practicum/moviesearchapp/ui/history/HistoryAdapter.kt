package com.practicum.moviesearchapp.ui.history

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.practicum.moviesearchapp.domain.models.Movie

class HistoryAdapter: RecyclerView.Adapter<HistoryViewHolder>() {

    var movies = ArrayList<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder = HistoryViewHolder(parent)

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(movies.get(position))
    }


}