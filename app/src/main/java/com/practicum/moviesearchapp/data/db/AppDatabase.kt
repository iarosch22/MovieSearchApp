package com.practicum.moviesearchapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.practicum.moviesearchapp.data.db.dao.MovieDao
import com.practicum.moviesearchapp.data.db.entity.MovieEntity

@Database(version = 1, entities = [MovieEntity::class])
abstract class AppDatabase: RoomDatabase() {

    abstract fun movieDao(): MovieDao

}