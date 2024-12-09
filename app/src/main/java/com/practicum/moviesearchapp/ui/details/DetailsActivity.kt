package com.practicum.moviesearchapp.ui.details

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.practicum.moviesearchapp.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private lateinit var tabMediator: TabLayoutMediator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val poster = intent.getStringExtra("poster") ?: ""
        val id = intent.getStringExtra("id") ?: ""


        binding.pager.adapter = DetailsViewPagerAdapter(
            fragmentManager = supportFragmentManager,
            lifecycle = lifecycle,
            posterUrl = poster,
            movieId = id
        )

        tabMediator = TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            when(position) {
                0 -> tab.text = "ПОСТЕР"
                1 -> tab.text = "О ФИЛЬМЕ"
            }
        }

        tabMediator.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
        tabMediator.detach()
    }

}