package com.example.apimlb.view

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.apimlb.R
import com.example.apimlb.components.MainButton

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeView(navController: NavController){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Image(
                        painter = painterResource(id = R.drawable.costuras),
                        contentDescription = "Logo_MLB",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(255, 253, 232)
                ),
            )
        }
    ){ innerPadding ->
        ContentHomeView(navController, Modifier.padding(innerPadding))
    }
}
@Composable
fun ContentHomeView(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        // Subtitulo y redireccionamiento a MLB grid
        // Añadimos varios imports que nos permiten salir a la red (En esta caso al MLB grid)
        val context = LocalContext.current

        MainButton(
            backColor = Color(255, 253, 232),
            color = Color.Black,
            modifier = Modifier
                .weight(0.2f),
            onClick = {
                val url = "https://www.sports-reference.com/immaculate-grid/"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(intent)
            }
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "\uD83E\uDDE0 MLB Grid",
                    fontSize = 20.sp
                )
                Text(
                    text = "Que tanto sabes de MLB",
                    modifier = Modifier.padding(top = 30.dp)
                )
            }
        }

        // Redireccionamiento a Juegos del Día
        MainButton(
            backColor = Color(255, 253, 232),
            color = Color.Black,
            modifier = Modifier
                .weight(0.2f),
            onClick = {navController.navigate("Juegos")}
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "⚾  Juegos del Día",
                    fontSize = 20.sp
                )
                Text(
                    text = "Mantente al tanto de tu equipo favorito",
                    modifier = Modifier.padding(top = 30.dp)
                )
            }
        }

        // Redireccionamiento a Standings MLB
        MainButton(
            backColor = Color(255, 253, 232),
            color = Color.Black,
            modifier = Modifier
                .weight(0.2f),
            onClick = {navController.navigate("Standings")}
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "\uD83D\uDCCA  Standings MLB",
                    fontSize = 20.sp
                )
                Text(
                    text = "Recuerda el mejor año de tu equipo",
                    modifier = Modifier.padding(top = 30.dp)
                )
            }
        }

        MainButton(
            backColor = Color(255, 253, 232),
            color = Color.Black,
            modifier = Modifier
                .weight(0.4f),
            onClick = {}
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Aqui va una frase matona",
                    fontSize = 20.sp
                )
                Text(
                    text = "Te amo Edgar Robles",
                    modifier = Modifier.padding(top = 30.dp)
                )
            }
        }
    }
}
