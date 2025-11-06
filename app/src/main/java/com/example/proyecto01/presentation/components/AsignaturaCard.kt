package com.example.proyecto01.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.proyecto01.domain.model.Asignatura

@Composable
fun AsignaturaCard(asignatura: Asignatura) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "Icono de asignatura",
                modifier = Modifier
                    .size(48.dp)
                    .padding(bottom = 8.dp),
                tint = MaterialTheme.colorScheme.primary
            )

            Text(asignatura.asignaturaNombre, style = MaterialTheme.typography.titleSmall)
            Text("Docente: ${asignatura.asignaturaDocente}", style = MaterialTheme.typography.bodySmall)
        }
    }
}
