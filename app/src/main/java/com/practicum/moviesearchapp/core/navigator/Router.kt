package com.practicum.moviesearchapp.core.navigator

import androidx.fragment.app.Fragment

interface Router {

    val navigatorHolder: NavigatorHolder

    fun openFragment(fragment: Fragment)

}