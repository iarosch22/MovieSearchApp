package com.practicum.moviesearchapp.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.google.android.material.tabs.TabLayoutMediator
import com.practicum.moviesearchapp.databinding.FragmentDetailsBinding
import com.practicum.moviesearchapp.ui.BindingFragment

class DetailsFragment: BindingFragment<FragmentDetailsBinding>() {

    private lateinit var tabMediator: TabLayoutMediator

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailsBinding {
        return FragmentDetailsBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val poster = requireArguments().getString(ARGS_POSTER_KEY) ?: ""
        val id = requireArguments().getString(ARG_ID_KEY) ?: ""

        binding.pager.adapter = DetailsViewPagerAdapter(
            fragmentManager = childFragmentManager,
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

    override fun onDestroyView() {
        super.onDestroyView()

        tabMediator.detach()
    }

    companion object {
        private const val ARGS_POSTER_KEY = "POSTER_KEY"
        private const val ARG_ID_KEY = "ID_KEY"

        const val TAG = "DetailsFragment"

        fun newInstance(poster: String, id: String) = DetailsFragment().apply {
            arguments = bundleOf(ARGS_POSTER_KEY to poster, ARG_ID_KEY to id)
        }
    }

}