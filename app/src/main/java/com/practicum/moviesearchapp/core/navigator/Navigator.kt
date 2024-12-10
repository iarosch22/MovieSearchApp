package com.practicum.moviesearchapp.core.navigator

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

interface Navigator {

    val fragmentContainerViewId: Int
    val fragmentManager: FragmentManager

    fun openFragment(fragment: Fragment)

}