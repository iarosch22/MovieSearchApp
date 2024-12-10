package com.practicum.moviesearchapp.core.navigator.impl

import androidx.fragment.app.Fragment
import com.practicum.moviesearchapp.core.navigator.Navigator
import com.practicum.moviesearchapp.core.navigator.NavigatorHolder

class NavigatorHolderImpl: NavigatorHolder {

    private var navigator: Navigator? = null

    override fun attachNavigator(navigator: Navigator) {
        this.navigator = navigator
    }

    override fun detachNavigator() {
        this.navigator = null
    }

    override fun openFragment(fragment: Fragment) {
        navigator?.openFragment(fragment)
    }

}