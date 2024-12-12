package com.practicum.moviesearchapp.ui.names

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.practicum.moviesearchapp.domain.models.Name

class NamesAdapter: RecyclerView.Adapter<NamesViewHolder>() {

    var names = ArrayList<Name>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NamesViewHolder = NamesViewHolder(parent)

    override fun getItemCount(): Int = names.size

    override fun onBindViewHolder(holder: NamesViewHolder, position: Int) {
        holder.bind(names[position])
    }

}