package com.littleapp.dictionary.utils

import android.content.Context
import android.content.Intent
import android.os.Bundle

fun Context.launchActivity(cls: Class<*>, extras: Bundle? = null) {
    val intent = Intent(this, cls).apply {
        extras?.let { putExtras(it) }
    }
    startActivity(intent)
}