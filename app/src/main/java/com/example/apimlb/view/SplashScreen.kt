package com.example.apimlb.view

import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.apimlb.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(key1 = true) {
        delay(1000)
        navController.navigate(route = "Home") {
            popUpTo(route = "Home") {
            }
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0, 0, 0))
    ) {
        Image(
            // La app se hará con equipos de la MLB
            painter = painterResource(id= R.drawable.batting),
            contentDescription = "MLBTracker",
            modifier = Modifier.fillMaxSize()
        )

        Text(
            text = "MLB\nTracker",
            color = Color.White,
            fontSize = 72.sp,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center,
            style = TextStyle(
                shadow = Shadow(
                    color = Color.Black,
                    offset = Offset(0f, 0f),
                    blurRadius = 15f
                )
            )
        )
    }
}