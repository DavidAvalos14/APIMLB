package com.example.apimlb.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.apimlb.components.MainIconButton
import com.example.apimlb.components.StandingCard
import com.example.apimlb.components.TitleBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import com.example.apimlb.viewModel.StandingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun StandingsView(navController: NavController) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { TitleBar("STANDINGS") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(255, 253, 232)
                ),
                navigationIcon = {
                    MainIconButton(icon = Icons.Default.ArrowBack) {
                        navController.popBackStack()
                    }
                }
            )
        }
    ) { innerPadding ->
        ContentStandingView(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun ContentStandingView(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: StandingsViewModel = viewModel()
) {
    val availableSeasons = (2016..2025).toList().reversed()
    var selectedYear by remember { mutableStateOf(availableSeasons.first()) }
    var expanded by remember { mutableStateOf(false) }

    LaunchedEffect(selectedYear) {
        viewModel.loadStandings(selectedYear)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        ) {
            Button(onClick = { expanded = true }) {
                Text("Season: $selectedYear")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                availableSeasons.forEach { year ->
                    DropdownMenuItem(
                        text = { Text(year.toString()) },
                        onClick = {
                            selectedYear = year
                            expanded = false
                        }
                    )
                }
            }
        }

        if (viewModel.isLoading) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
            return
        }

        viewModel.errorMessage?.let { error ->
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(error, color = MaterialTheme.colorScheme.error)
            }
            return
        }

        LazyColumn {
            viewModel.standingsRecords
                .groupBy { it.division.league.name }
                .forEach { (leagueName, recordsInLeague) ->

                    // Encabezado de liga
                    item {
                        Text(
                            text = leagueName,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }

                    recordsInLeague.forEach { record ->

                        // Encabezado de división
                        item {
                            Text(
                                text = "--- ${record.division.name} ---",
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.DarkGray,
                                modifier = Modifier.padding(
                                    start = 8.dp,
                                    top = 4.dp,
                                    bottom = 4.dp
                                )
                            )
                        }

                        // Equipos
                        items(record.teamRecords) { team ->
                            StandingCard(team)
                        }
                    }
                }
        }
    }
}
