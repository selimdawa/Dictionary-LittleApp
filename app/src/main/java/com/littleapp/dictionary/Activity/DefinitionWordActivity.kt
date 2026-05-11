package com.littleapp.dictionary.Activity

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.littleapp.dictionary.Unit.DATA
import com.littleapp.dictionary.Unit.THEME
import com.littleapp.dictionary.databinding.ActivityDefinitionWordBinding

class DefinitionWordActivity : AppCompatActivity() {

    private var binding: ActivityDefinitionWordBinding? = null
    var context: Context = this@DefinitionWordActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        THEME.setThemeOfApp(context)
        super.onCreate(savedInstanceState)
        binding = ActivityDefinitionWordBinding.inflate(layoutInflater)
        val view = binding!!.root
        setContentView(view)

        binding!!.toolbar.nameSpace.text = DATA.meaning_of_the_word
        binding!!.tvDefinition.text = intent.getStringExtra(DATA.DICTIONARY_KEY)
    }
}