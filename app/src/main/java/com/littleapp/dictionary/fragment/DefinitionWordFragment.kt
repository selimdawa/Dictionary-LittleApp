package com.littleapp.dictionary.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.littleapp.dictionary.R
import com.littleapp.dictionary.viewmodel.MainViewModel
import com.littleapp.dictionary.databinding.FragmentDefinitionWordBinding
import com.littleapp.dictionary.utils.DATA
import com.littleapp.dictionary.utils.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DefinitionWordFragment : Fragment() {

    private var _binding: FragmentDefinitionWordBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by hiltNavGraphViewModels(R.id.nav_graph)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDefinitionWordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        observeViewModel()
    }

    private fun setupToolbar() {
        binding.toolbar.apply {
            nameSpace.text = DATA.MEANING_OF_THE_WORD
            imageLeft.apply {
                visibility = View.VISIBLE
                setImageResource(R.drawable.ic_back)
                setOnClickListener {
                    findNavController().popBackStack()
                }
            }
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    if (state is UiState.Success) {
                        binding.tvDefinition.text = state.data
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}