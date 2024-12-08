package com.practicum.moviesearchapp.ui.movieCast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.practicum.moviesearchapp.databinding.ListItemCastBinding
import com.practicum.moviesearchapp.domain.models.MovieCastPerson

class MoviesCastAdapter: RecyclerView.Adapter<MovieCastViewHolder>() {

    var movieCastList = ArrayList<MovieCastPerson>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCastViewHolder {
        val layoutInspector = LayoutInflater.from(parent.context)

        return MovieCastViewHolder(ListItemCastBinding.inflate(layoutInspector, parent, false))
    }

    override fun getItemCount(): Int = movieCastList.size

    override fun onBindViewHolder(holder: MovieCastViewHolder, position: Int) {
        holder.bind(movieCastList[position])
    }

}