package com.littleapp.dictionary.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import com.littleapp.dictionary.R
import com.littleapp.dictionary.Unit.DATA
import com.littleapp.dictionary.ViewModel.MainViewModel
import com.littleapp.dictionary.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by hiltNavGraphViewModels(R.id.nav_graph)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.nameSpace.text = DATA.DICTIONARY
        
        binding.findButton.setOnClickListener {
            val word = binding.searchEditText.text.toString()
            if (word.isNotEmpty()) {
                viewModel.searchWord(word)
            } else {
                Toast.makeText(requireContext(), "Please enter a word", Toast.LENGTH_SHORT).show()
            }
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.definition.observe(viewLifecycleOwner) { _ ->
            // The data is already in the Shared ViewModel
            findNavController().navigate(R.id.action_mainFragment_to_definitionWordFragment)
        }

        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
