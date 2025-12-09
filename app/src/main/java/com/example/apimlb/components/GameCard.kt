package com.example.apimlb.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.example.apimlb.model.MlbGame

@Composable
fun GameCard(game: MlbGame) {

    // Sacamos los scores para comparar
    val awayScore = game.teams.away.score
    val homeScore = game.teams.home.score

    // Determinamos ganador
    val awayWinner = awayScore != null && homeScore != null && awayScore > homeScore
    val homeWinner = awayScore != null && homeScore != null && homeScore > awayScore

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                // IZQUIERDA → Equipos
                Column(modifier = Modifier.weight(1f)) {

                    // Visitante
                    Text(
                        text = "${game.teams.away.team.name}  ${awayScore ?: "-"}",
                        fontWeight = if (awayWinner) FontWeight.ExtraBold else FontWeight.Normal,
                        color = if (awayWinner) Color.Black else Color.Unspecified
                    )

                    // Local
                    Text(
                        text = "${game.teams.home.team.name}  ${homeScore ?: "-"}",
                        fontWeight = if (homeWinner) FontWeight.ExtraBold else FontWeight.Normal,
                        color = if (homeWinner) Color.Black else Color.Unspecified
                    )
                }

                // LÍNEA VERTICAL
                Box(
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .width(1.dp)
                        .height(40.dp)
                        .background(Color.Gray.copy(alpha = 0.4f))
                )

                // DERECHA → Estado del juego
                Text(
                    text = game.status.detailedState,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}
