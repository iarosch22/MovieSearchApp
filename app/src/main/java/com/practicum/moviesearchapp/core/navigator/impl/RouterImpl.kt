package com.practicum.moviesearchapp.core.navigator.impl

import androidx.fragment.app.Fragment
import com.practicum.moviesearchapp.core.navigator.NavigatorHolder
import com.practicum.moviesearchapp.core.navigator.Router

class RouterImpl: Router {

    override val navigatorHolder: NavigatorHolder = NavigatorHolderImpl()

    override fun openFragment(fragment: Fragment) {
        navigatorHolder.openFragment(fragment)
    }

}