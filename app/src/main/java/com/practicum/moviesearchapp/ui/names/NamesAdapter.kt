package com.practicum.moviesearchapp.ui.names

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.practicum.moviesearchapp.domain.models.Person

class NamesAdapter: RecyclerView.Adapter<NamesViewHolder>() {

    var persons = ArrayList<Person>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NamesViewHolder = NamesViewHolder(parent)

    override fun getItemCount(): Int = persons.size

    override fun onBindViewHolder(holder: NamesViewHolder, position: Int) {
        holder.bind(persons[position])
    }

}