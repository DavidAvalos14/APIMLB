package com.example.apimlb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.apimlb.navigation.NavManager
import com.example.apimlb.ui.theme.APIMLBTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            APIMLBTheme() {
                NavManager()
                //SplashScreen()
            }
        }
    }
}


