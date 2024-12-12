package com.practicum.moviesearchapp.ui.info

import android.view.LayoutInflater
import android.view.ViewGroup
import com.practicum.moviesearchapp.databinding.FragmentInfoBinding
import com.practicum.moviesearchapp.ui.BindingFragment

class InfoFragment : BindingFragment<FragmentInfoBinding>() {

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentInfoBinding {
        return FragmentInfoBinding.inflate(inflater, container, false)
    }

}