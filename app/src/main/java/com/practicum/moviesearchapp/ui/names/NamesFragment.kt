package com.practicum.moviesearchapp.ui.names

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.practicum.moviesearchapp.databinding.FragmentNamesBinding
import com.practicum.moviesearchapp.domain.models.Person
import com.practicum.moviesearchapp.presentation.names.NamesViewModel
import com.practicum.moviesearchapp.ui.BindingFragment
import com.practicum.moviesearchapp.ui.names.models.NamesState
import org.koin.androidx.viewmodel.ext.android.viewModel

class NamesFragment: Fragment() {

    private val adapter = NamesAdapter()

    private lateinit var binding: FragmentNamesBinding

    private var textWatcher: TextWatcher? = null

    private val viewModel by viewModel<NamesViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNamesBinding.inflate(inflater, container, false)
        return binding.root
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

    override fun onDestroyView() {
        super.onDestroyView()
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

    private fun showContent(persons: List<Person>) {
        binding.namesRV.isVisible = true
        binding.placeholderMessage.isVisible = false
        binding.progressBar.isVisible = false

        adapter.persons.clear()
        adapter.persons.addAll(persons)
        adapter.notifyDataSetChanged()
    }

    private fun render(state: NamesState) {
        when(state) {
            is NamesState.Content -> showContent(state.persons)
            is NamesState.Empty -> showEmpty(state.message)
            is NamesState.Error -> showError(state.errorMessage)
            NamesState.Loading -> showLoading()
        }
    }

}