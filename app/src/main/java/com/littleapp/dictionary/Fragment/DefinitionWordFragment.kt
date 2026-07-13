package com.littleapp.dictionary.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.littleapp.dictionary.R
import com.littleapp.dictionary.Unit.DATA
import com.littleapp.dictionary.ViewModel.MainViewModel
import com.littleapp.dictionary.databinding.FragmentDefinitionWordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DefinitionWordFragment : Fragment() {

    private var _binding: FragmentDefinitionWordBinding? = null
    private val binding get() = _binding!!

    // Accessing the same ViewModel instance scoped to the NavGraph
    private val viewModel: MainViewModel by hiltNavGraphViewModels(R.id.nav_graph)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDefinitionWordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.nameSpace.text = DATA.meaning_of_the_word
        
        viewModel.definition.observe(viewLifecycleOwner) { definition ->
            binding.tvDefinition.text = definition
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
