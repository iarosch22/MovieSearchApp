package com.practicum.moviesearchapp.ui.root

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.practicum.moviesearchapp.R
import com.practicum.moviesearchapp.databinding.ActivityRootBinding
import com.practicum.moviesearchapp.ui.movies.MoviesFragment

class RootActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRootBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                add<MoviesFragment>(R.id.rootFragmentContainer)
            }
        }
    }

}