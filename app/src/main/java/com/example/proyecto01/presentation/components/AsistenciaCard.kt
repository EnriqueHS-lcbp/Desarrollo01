package com.example.proyecto01.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale


@Composable
fun AsistenciaCard(
    dia: String,
    fechaDia: String,
    horInicio: String,
    horFin: String,
    clase: String,
    sesion: String,
    asistio: Int
) {

    val borderColor = when (asistio) {
        1 -> Color(0xFF4CAF50) // Verde
        0 -> Color(0xFFF44336) // Rojo
        else -> Color(0xFFFFC107) // Amarillo
    }

    val fecha = try {
        LocalDate.parse(fechaDia)
    } catch (e: Exception) {
        null
        println(e)
    }
  /*
    val diaTexto = fecha?.dayOfMonth?.toString() ?: "?"
    val mesTexto = fecha?.month?.getDisplayName(TextStyle.FULL, Locale("es"))?.replaceFirstChar { it.uppercase() } ?: "?"
*/

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp, horizontal = 2.dp)
            .border(width = 1.dp, color = borderColor),
        //elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Columna 1: Día y Fecha
            Column(modifier = Modifier.weight(1f)) {
                Text("Día", style = MaterialTheme.typography.labelSmall)
                Text(dia, style = MaterialTheme.typography.bodySmall)
                Spacer(Modifier.height(4.dp))
                //Text(dia, style = MaterialTheme.typography.bodyMedium)
                Text("Fecha", style = MaterialTheme.typography.labelSmall)
                Text(fechaDia, style = MaterialTheme.typography.bodySmall)
            }

            // Columna 2: Hora Inicio y Fin
            Column(modifier = Modifier.weight(1f)) {
                Text("H. Inicio", style = MaterialTheme.typography.labelSmall)
                Text(horInicio, style = MaterialTheme.typography.bodySmall)
                Spacer(Modifier.height(4.dp))
                Text("H. Fin", style = MaterialTheme.typography.labelSmall)
                Text(horFin, style = MaterialTheme.typography.bodySmall)
            }

            // Columna 3: Clase y Sesión
            Column(modifier = Modifier.weight(1f)) {

                Text("Clase", style = MaterialTheme.typography.labelSmall)
                Text(clase, style = MaterialTheme.typography.bodySmall)
                Spacer(Modifier.height(8.dp))
                Text("Sesión", style = MaterialTheme.typography.labelSmall)
                Text(sesion, style = MaterialTheme.typography.bodySmall)
            }

            // Columna 4: Asistencia
            /*Column(
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                val (icon, iconColor) = when (asistio) {
                    1 -> Icons.Default.Check to Color(0xFF4CAF50)
                    0 -> Icons.Default.Close to Color(0xFFF44336)
                    else -> Icons.Default.ArrowDropDown to Color(0xFFFFC107)

                }

                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(iconColor),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null, // si no necesitas accesibilidad
                        tint = iconColor //Color.White
                    )
                }
            }*/
        }
    }
}
