package com.rodrigoaads.mytime

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import com.rodrigoaads.mytime.ui.navigation.MyTimeNavHost
import com.rodrigoaads.mytime.ui.theme.MyTimeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyTimeTheme {
                CompositionLocalProvider(LocalActivity provides this) {
                    MyTimeNavHost()
                }
            }
        }
    }

    fun openUrl(url: String) {
        try {
            val webPage: Uri = Uri.parse(url)
            val intent = Intent(Intent.ACTION_VIEW, webPage)
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(
                this,
                getString(R.string.invalid_url_text),
                Toast.LENGTH_SHORT
            ).show()
        }
        
    }
}

val LocalActivity = staticCompositionLocalOf<MainActivity> {
    error("CompositionLocal MainActivity not present")
}