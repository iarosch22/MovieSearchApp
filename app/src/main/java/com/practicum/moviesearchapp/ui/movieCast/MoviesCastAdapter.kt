package com.practicum.moviesearchapp.ui.movieCast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.practicum.moviesearchapp.R
import com.practicum.moviesearchapp.databinding.ListItemCastBinding
import com.practicum.moviesearchapp.databinding.ListItemHeaderBinding
import com.practicum.moviesearchapp.domain.models.MovieCastPerson
import com.practicum.moviesearchapp.presentation.movieCast.MoviesCastRVItem

class MoviesCastAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items = emptyList<MoviesCastRVItem>()

    override fun getItemViewType(position: Int): Int {
        return when(items[position]) {
            is MoviesCastRVItem.HeaderItem -> R.layout.list_item_header
            is MoviesCastRVItem.PersonItem -> R.layout.list_item_cast
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = when(viewType) {
            R.layout.list_item_header -> MovieCastHeaderViewHolder(ListItemHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            R.layout.list_item_cast -> MovieCastViewHolder(ListItemCastBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> error("Unknown viewType create [$viewType]")
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        when(holder.itemViewType) {
            R.layout.list_item_header -> {
                val headerHolder = holder as MovieCastHeaderViewHolder
                headerHolder.bind(items[position] as MoviesCastRVItem.HeaderItem)
            }
            R.layout.list_item_cast -> {
                val castHolder = holder as MovieCastViewHolder
                castHolder.bind(items[position] as MoviesCastRVItem.PersonItem)
            }
            else -> error("Unknown viewType bind [${holder.itemViewType}]")
        }
    }

    override fun getItemCount(): Int = items.size

}