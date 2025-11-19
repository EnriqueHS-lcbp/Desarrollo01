package com.example.proyecto01.presentation.asistencia

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

import androidx.compose.ui.unit.dp
import com.example.proyecto01.presentation.components.AsignaturaCard
import com.example.proyecto01.presentation.components.DropdownSelector
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.proyecto01.data.remote.model.CarreraRequest
import com.example.proyecto01.presentation.asistenciaAsignatura.state.AsignaturaAsistenciaUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AsistenciaScreen(
    idEstudiante: Int,
    viewModel: AsistenciaViewModel,
    navController: NavController
) {
    val uiState by viewModel.uiState.collectAsState()
    val asignaturasState by viewModel.asignaturasUiState.collectAsState()


    LaunchedEffect(Unit) {
        val request = CarreraRequest(id_estud = idEstudiante)
        viewModel.loadInitialData(request)
    }

    Scaffold { padding ->
        if (uiState.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (uiState.error != null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text(text = uiState.error ?: "Error desconocido")
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
            ) {
                item(span = { GridItemSpan(2) }) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.primary)
                                .padding(horizontal = 16.dp, vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
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
                                text = "Asistencia",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }

                        Spacer(Modifier.height(8.dp))

                        DropdownSelector(
                            label = "Carrera",
                            options = uiState.carreras,
                            selected = uiState.carreraSeleccionada,
                            optionLabel = {it.serv_nombre},
                            onSelected = viewModel::onCarreraSelected
                        )

                        Spacer(Modifier.height(8.dp))

                        DropdownSelector(
                            label = "Periodo Académico",
                            options = uiState.periodos,
                            selected = uiState.periodoSeleccionado,
                            optionLabel = { it.peracad_nombre },
                            onSelected = viewModel::onPeriodoSelected
                        )

                        Spacer(Modifier.height(8.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.primary)
                                .padding(horizontal = 16.dp, vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Asignaturas",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onPrimary
                            )

                            Icon(
                                imageVector = Icons.Default.List,
                                contentDescription = "Icono de asignaturas",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                }

                items(asignaturasState.asignaturas, span = { GridItemSpan(2) }) { asignatura ->

                    AsignaturaCard(
                        asignatura,
                        onClick = {
                            val id_stud_pe= uiState.periodoSeleccionado?.id_estud_pe
                            System.out.println("Asignatura: ${id_stud_pe}")
                            System.out.println("Asignatura: ${asignatura.pest_asign_nombre}")
                            val nombreAsignaturaEncoded = Uri.encode(asignatura.pest_asign_nombre)
                            navController.navigate("detalle_asistencia/${id_stud_pe}/${asignatura.id_matric_asig_secc}/${nombreAsignaturaEncoded}/${asignatura.total_max_inas}/${asignatura.pest_det_asis_min}")
                        })
                }

                /*items(asignaturasState.asignaturas) { asignatura ->
                    AsignaturaCard(asignatura)
                }*/
            }
        }
    }



}