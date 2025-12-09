package com.example.apimlb.view

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.apimlb.R
import com.example.apimlb.components.GameCard
import com.example.apimlb.components.MainIconButton
import com.example.apimlb.components.TitleBar
import com.example.apimlb.viewmodel.JuegosViewModel
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun JuegosView(navController: NavController){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {TitleBar("JUEGOS")},
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(255, 253, 232)
                ),
                navigationIcon= {
                    MainIconButton(icon = Icons.Default.ArrowBack) {
                        navController.popBackStack()
                    }
                }
            )
        }
    ){ innerPadding ->
        ContentJuegoView(navController, Modifier.padding(innerPadding))
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentJuegoView(navController: NavController, modifier: Modifier = Modifier) {
    // Estado del diálogo
    val datePickerState = rememberDatePickerState()
    var showDialog by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }
    val selectedDateText = selectedDate?.toString() ?: "Selecciona una fecha"

    // Instancia del ViewModel
    val viewModel = remember { JuegosViewModel() }
    LaunchedEffect(selectedDate) {
        selectedDate?.let { date ->
            // El formato para la API debe ser YYYY-MM-DD
            viewModel.loadGames(date.toString())
        }
    }

    if (showDialog) {
        DatePickerDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    val millis = datePickerState.selectedDateMillis
                    // Si el usuario confirma, actualizamos nuestro estado 'selectedDate'
                    if (millis != null) {
                        val localDate = Instant.ofEpochMilli(millis)
                            .atZone(ZoneId.of("UTC"))
                            .toLocalDate()
                        selectedDate = localDate
                    }
                    showDialog = false
                }) {
                    Text("Aceptar")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancelar")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Botón para abrir el selector de fecha
        Button(
            onClick = { showDialog = true },
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                containerColor = Color(0, 0, 0), // azul agradable
                contentColor = Color.White
            ),
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Seleccionar fecha")
        }


        Spacer(modifier = Modifier.height(16.dp))

        // Muestra la fecha seleccionada o el texto por defecto
        Text(
            text = selectedDateText,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
                .padding(12.dp)
                .then(
                    Modifier
                        .padding(4.dp)
                )
                .background(Color(255, 253, 232), shape = MaterialTheme.shapes.medium)
                .padding(12.dp)
        )


        Spacer(modifier = Modifier.height(32.dp))


        Column(modifier = Modifier.padding(16.dp)) {
            // Mostrar juegos obtenidos por el ViewModel
            viewModel.games.forEach { game ->
                GameCard(game)
            }
        }
    }
}
