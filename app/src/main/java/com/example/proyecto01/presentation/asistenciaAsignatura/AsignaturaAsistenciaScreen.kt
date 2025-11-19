package com.example.proyecto01.presentation.asistenciaAsignatura

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyecto01.data.remote.dto.AsignaturaAsistenciaDto
import com.example.proyecto01.data.remote.model.AsignaturaAsisitenciaRequest
import com.example.proyecto01.presentation.asistenciaAsignatura.state.AsignaturaAsistenciaUiState
import com.example.proyecto01.presentation.components.AsistenciaCard
import java.lang.Integer.parseInt
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun AsignaturaAsistenciaScreen(
    idEstudiante: Int,
    idAsignatura: Int,
    asigNombre: String,
    inasisMax: String,
    inasisTotal: String,
    viewModel: AsignaturaAsistenciaViewModel,
    navController: NavController
) {


    val asignaturasAsistenciaUiState by viewModel.asignaturasAsistenciaUiState.collectAsState()

    //val asignaturaAsistencia = AsignaturaAsisitenciaRequest(idEstudiante, idAsignatura)

    LaunchedEffect(Unit) {
        val request = AsignaturaAsisitenciaRequest(
            id_estud_pe =idEstudiante,
            id_matric_asig_secc = idAsignatura
        )
        viewModel.loadInitialData(request)
    }

    Scaffold { padding ->
        if (asignaturasAsistenciaUiState.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (asignaturasAsistenciaUiState.error != null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text(text = asignaturasAsistenciaUiState.error ?: "Error desconocido")
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "Regresar",
                        modifier = Modifier
                            .size(40.dp)
                            .clickable { navController.popBackStack() },
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = asigNombre,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Inasistencias Permitidas: $inasisMax%",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Inasistencias Actuales: $inasisTotal",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }

                // Tabs
                TabRow(selectedTabIndex = asignaturasAsistenciaUiState.tabIndex) {
                    listOf("Anterior", "Posterior").forEachIndexed { index, title ->
                        Tab(
                            selected = asignaturasAsistenciaUiState.tabIndex == index,
                            onClick = { viewModel.onTabSelected(index) },
                            text = { Text(title) }
                        )
                    }
                }

                // Contenido del tab
                when (asignaturasAsistenciaUiState.tabIndex) {
                    0 -> PasadoTabContent(asignaturasAsistenciaUiState, modifier = Modifier.weight(1f))
                    1 -> FuturoTabContent(asignaturasAsistenciaUiState, modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

fun filtrarAsistenciasPorFecha(
    asistencias: List<AsignaturaAsistenciaDto>,
    antesDeHoy: Boolean
): List<AsignaturaAsistenciaDto> {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val hoy = LocalDate.now()

    return asistencias.filter {
        try {
            val fecha = LocalDate.parse(it.fecha_dia, formatter)
            if (antesDeHoy) fecha.isBefore(hoy) else fecha.isAfter(hoy)
        } catch (e: Exception) {
            false
        }
    }
}


@Composable
fun PasadoTabContent(
    uiState: AsignaturaAsistenciaUiState,
    modifier: Modifier = Modifier
) {
    val asistenciasPasadas = remember(uiState.asistenciasAsignatura) {
        filtrarAsistenciasPorFecha(uiState.asistenciasAsignatura, antesDeHoy = true)
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(asistenciasPasadas) { asistencia ->

            AsistenciaCard(
                dia = asistencia.dia,
                fechaDia = asistencia.hor_asis_dia,
                horInicio = asistencia.hor_inicio,
                horFin = asistencia.hor_fin,
                clase = asistencia.clase,
                sesion = asistencia.sesion,
                asistio = parseInt(asistencia.asistio)
            )
        }
    }
}

@Composable
fun FuturoTabContent(
    uiState: AsignaturaAsistenciaUiState,
    modifier: Modifier = Modifier
) {
    val asistenciasPasadas = remember(uiState.asistenciasAsignatura) {
        filtrarAsistenciasPorFecha(uiState.asistenciasAsignatura, antesDeHoy = false)
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(asistenciasPasadas) { asistencia ->
            asistencia.fecha_dia
            AsistenciaCard(
                dia = asistencia.dia,
                fechaDia = asistencia.fecha_dia,
                horInicio = asistencia.hor_inicio,
                horFin = asistencia.hor_fin,
                clase = asistencia.clase,
                sesion = asistencia.sesion,
                asistio = parseInt(asistencia.asistio)
            )
        }
    }
}
