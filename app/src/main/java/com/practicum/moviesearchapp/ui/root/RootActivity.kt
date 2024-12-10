package com.practicum.moviesearchapp.ui.root

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.practicum.moviesearchapp.R
import com.practicum.moviesearchapp.core.navigator.NavigatorHolder
import com.practicum.moviesearchapp.core.navigator.impl.NavigatorImpl
import com.practicum.moviesearchapp.databinding.ActivityRootBinding
import com.practicum.moviesearchapp.ui.movies.MoviesFragment
import org.koin.android.ext.android.inject

class RootActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRootBinding

    private val navigatorHolder: NavigatorHolder by inject()

    private val navigator = NavigatorImpl(
        R.id.rootFragmentContainer,
        supportFragmentManager
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            navigator.openFragment(MoviesFragment())
        }
    }

    override fun onResume() {
        super.onResume()

        navigatorHolder.attachNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()

        navigatorHolder.detachNavigator()
    }

}