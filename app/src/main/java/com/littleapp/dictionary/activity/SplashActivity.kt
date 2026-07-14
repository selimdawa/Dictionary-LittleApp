package com.littleapp.dictionary.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.littleapp.dictionary.databinding.ActivitySplashBinding
import com.littleapp.dictionary.utils.CLASS
import com.littleapp.dictionary.utils.THEME
import com.littleapp.dictionary.utils.launchActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private var _binding: ActivitySplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        THEME.setThemeOfApp(this)
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            delay(2.seconds)
            launchMain()
        }
    }

    private fun launchMain() {
        launchActivity(CLASS.MAIN)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}