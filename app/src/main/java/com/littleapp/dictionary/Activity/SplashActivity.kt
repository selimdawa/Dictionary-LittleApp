package com.littleapp.dictionary.Activity

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.littleapp.dictionary.Unit.CLASS
import com.littleapp.dictionary.Unit.THEME
import com.littleapp.dictionary.Unit.VOID
import com.littleapp.dictionary.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private var _binding: ActivitySplashBinding? = null
    private val binding get() = _binding!!

    var context: Context = this@SplashActivity

    var time_per_second = 2
    var time_final = time_per_millis * time_per_second

    override fun onCreate(savedInstanceState: Bundle?) {
        THEME.setThemeOfApp(context)
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            delay(time_final.toLong())
            launchMain()
        }
    }

    private fun launchMain() {
        VOID.Intent1(context, CLASS.MAIN)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val time_per_millis = 1000
    }
}
