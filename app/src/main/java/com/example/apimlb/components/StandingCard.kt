package com.example.apimlb.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.apimlb.model.StandingTeam

@Composable
fun StandingCard(team: StandingTeam) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier
                .background(Color(255, 253, 232))
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Aquí no tenemos rank directo, puedes usar otro campo o índice en la lista
            Text(
                text = "#", // O el índice de la lista si lo pasas
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.width(40.dp)
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = team.team.name, // Nombre correcto
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Games Back: ${team.gamesBack}", // Ejemplo de otro dato
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }

            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "${team.leagueRecord.wins}-${team.leagueRecord.losses}",
                    style = MaterialTheme.typography.titleMedium
                )
                val pct = team.leagueRecord.wins.toFloat() /
                        (team.leagueRecord.wins + team.leagueRecord.losses)
                Text(
                    text = "%.3f".format(pct),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }
        }
    }
}
