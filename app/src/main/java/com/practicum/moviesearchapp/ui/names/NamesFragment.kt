package com.practicum.moviesearchapp.ui.names

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.practicum.moviesearchapp.databinding.FragmentNamesBinding
import com.practicum.moviesearchapp.domain.models.Name
import com.practicum.moviesearchapp.presentation.names.NamesViewModel
import com.practicum.moviesearchapp.ui.BindingFragment
import com.practicum.moviesearchapp.ui.names.models.NamesState
import org.koin.androidx.viewmodel.ext.android.viewModel

class NamesFragment: BindingFragment<FragmentNamesBinding>() {

    private val adapter = NamesAdapter()

    private val handler = Handler(Looper.getMainLooper())

    private var textWatcher: TextWatcher? = null

    private val viewModel by viewModel<NamesViewModel>()

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNamesBinding {
        return FragmentNamesBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.namesRV.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.namesRV.adapter = adapter

        textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.searchDebounce(changedText = s?.toString() ?: "")
            }

            override fun afterTextChanged(s: Editable?) {
            }
        }

        textWatcher?.let { binding.queryInput.addTextChangedListener(it) }

        viewModel.observeState().observe(viewLifecycleOwner) {
            render(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        textWatcher?.let { binding.queryInput.removeTextChangedListener(it) }
    }

    private fun showLoading() {
        binding.namesRV.isVisible = false
        binding.placeholderMessage.isVisible = false
        binding.progressBar.isVisible = true
    }

    private fun showError(errorMessage: String) {
        binding.namesRV.isVisible = false
        binding.placeholderMessage.isVisible = true
        binding.progressBar.isVisible = false

        binding.placeholderMessage.text = errorMessage
    }

    private fun showEmpty(errorMessage: String) {
        showError(errorMessage)
    }

    private fun showContent(names: List<Name>) {
        binding.namesRV.isVisible = true
        binding.placeholderMessage.isVisible = false
        binding.progressBar.isVisible = false

        adapter.names.clear()
        adapter.names.addAll(names)
        adapter.notifyDataSetChanged()
    }

    private fun render(state: NamesState) {
        when(state) {
            is NamesState.Content -> showContent(state.names)
            is NamesState.Empty -> showEmpty(state.message)
            is NamesState.Error -> showError(state.errorMessage)
            NamesState.Loading -> showLoading()
        }
    }

}