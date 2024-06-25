package com.rodrigoaads.mytime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.rodrigoaads.mytime.ui.navigation.MyTimeNavHost
import com.rodrigoaads.mytime.ui.pages.RegisterPage
import com.rodrigoaads.mytime.ui.theme.MyTimeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyTimeTheme {
                MyTimeNavHost()
            }
        }
    }
}