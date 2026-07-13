package com.littleapp.dictionary.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.littleapp.dictionary.Unit.DATA
import com.littleapp.dictionary.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONArray

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

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
        binding.findButton.setOnClickListener { stringRequest() }
    }

    private fun extractDefinitionFromJson(response: String) {
        val jsonArray = JSONArray(response)
        val firstIndex = jsonArray.getJSONObject(0)
        val getShotDefinition = firstIndex.getJSONArray(DATA.Short_Def)
        val firstShortDefinition = getShotDefinition.get(0)

        val action = MainFragmentDirections.actionMainFragmentToDefinitionWordFragment(firstShortDefinition.toString())
        findNavController().navigate(action)
    }

    private fun getUrl(): String {
        val word = binding.searchEditText.text
        val apiKey = DATA.DICTIONARY_API_KEY
        val basicUrl = DATA.DICTIONARY_BASIC_URL
        return "$basicUrl$word?key=$apiKey"
    }

    private fun stringRequest() {
        val url = getUrl()
        val queue = Volley.newRequestQueue(requireContext())
        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            try {
                extractDefinitionFromJson(response)
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }, { error ->
            Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
        })
        queue.add(stringRequest)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
