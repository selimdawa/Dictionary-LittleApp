package com.littleapp.dictionary.Activity

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import com.littleapp.dictionary.Unit.DATA
import com.littleapp.dictionary.Unit.THEME
import com.littleapp.dictionary.databinding.ActivityDefinitionWordBinding

@AndroidEntryPoint
class DefinitionWordActivity : AppCompatActivity() {

    private var _binding: ActivityDefinitionWordBinding? = null
    private val binding get() = _binding!!

    var context: Context = this@DefinitionWordActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        THEME.setThemeOfApp(context)
        super.onCreate(savedInstanceState)
        _binding = ActivityDefinitionWordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.nameSpace.text = DATA.meaning_of_the_word
        binding.tvDefinition.text = intent.getStringExtra(DATA.DICTIONARY_KEY)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}