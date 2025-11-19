package com.example.proyecto01.presentation.components

import android.R.attr.onClick
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.proyecto01.data.remote.dto.AsignaturaDto
import java.math.BigDecimal

@Composable
fun AsignaturaCard(
    asignatura: AsignaturaDto,
    onClick: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {

            AsyncImage(
                model = asignatura.peda_url_imagen,
                contentDescription = "Imagen del curso",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
            )

            Text(
                text = asignatura.pest_asign_nombre,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            )


            val colorInasistencia = when {
                asignatura.matric_asig_porc_inasistencia.toBigDecimal().compareTo(BigDecimal.ZERO) == 0 ->
                    MaterialTheme.colorScheme.onSurface

                asignatura.matric_asig_porc_inasistencia.toBigDecimal()
                    .compareTo(asignatura.pest_det_asis_min.toBigDecimal()) > 0 ->
                    Color.Red

                else -> Color(0xFFFFA000) // naranja de advertencia
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp))
                    .background(colorInasistencia)

                    .padding(horizontal = 12.dp, vertical = 2.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column {
                    Text("Permitidas", style = MaterialTheme.typography.labelSmall, color = Color.White)
                    Text("${asignatura.pest_det_asis_min}", style = MaterialTheme.typography.bodyMedium, color = Color.White)
                }
                Column {

                    Text("Actuales", style = MaterialTheme.typography.labelSmall, color = Color.White)
                    Text(
                        text = "${asignatura.matric_asig_porc_inasistencia}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White
                    )
                }
            }
        }
    }
}
