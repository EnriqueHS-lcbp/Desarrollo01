package com.example.proyecto01.presentation.asistencia

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

import androidx.compose.ui.unit.dp
import com.example.proyecto01.presentation.components.AsignaturaCard
import com.example.proyecto01.presentation.components.DropdownSelector
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.border
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.List

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AsistenciaScreen(viewModel: AsistenciaViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    Log.d("AsistenciaScreen","Entro al screen")
    Scaffold { padding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            item(span = { GridItemSpan(2) }) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    // 🔵 Encabezado tipo bloque para "Asistencia"
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.primary)
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        if (uiState.fotoUrl.isNotBlank()) {
                            Image(
                                painter = rememberAsyncImagePainter(uiState.fotoUrl),
                                contentDescription = "Foto del alumno",
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .border(1.dp, Color.Gray, CircleShape)
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = "Icono de usuario",
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .border(1.dp, Color.Gray, CircleShape),
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }

                        Spacer(Modifier.width(12.dp))

                        Text(
                            text = "Asistencia",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }

                    Spacer(Modifier.height(16.dp))

                    DropdownSelector(
                        label = "Carrera",
                        options = uiState.carreras,
                        selected = uiState.carreraSeleccionada,
                        onSelected = viewModel::onCarreraSelected
                    )

                    Spacer(Modifier.height(8.dp))

                    DropdownSelector(
                        label = "Periodo Académico",
                        options = uiState.periodos,
                        selected = uiState.periodoSeleccionado,
                        onSelected = viewModel::onPeriodoSelected
                    )

                    Spacer(Modifier.height(24.dp))

                    // 🔵 Encabezado tipo bloque para "Asignaturas"
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

            items(uiState.asignaturas) { asignatura ->
                AsignaturaCard(asignatura)
            }
        }
    }


    /*Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    if (uiState.fotoUrl.isNotBlank()) {
                        Image(
                            painter = rememberAsyncImagePainter(uiState.fotoUrl),
                            contentDescription = "Foto del alumno",
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .size(36.dp)
                                .clip(CircleShape)
                                .border(1.dp, Color.Gray, CircleShape)
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Icono de usuario",
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .size(36.dp)
                                .clip(CircleShape)
                                .border(1.dp, Color.Gray, CircleShape),
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                title = {
                    Text(
                        text = "Asistencia",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.height(56.dp)
            )
        }
    ) { padding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            item(span = { GridItemSpan(2) }) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Spacer(Modifier.height(16.dp))



                    DropdownSelector(
                        label = "Curso",
                        options = uiState.cursos,
                        selected = uiState.cursoSeleccionado,
                        onSelected = viewModel::onCursoSelected
                    )

                    Spacer(Modifier.height(8.dp))

                    DropdownSelector(
                        label = "Periodo Académico",
                        options = uiState.periodos,
                        selected = uiState.periodoSeleccionado,
                        onSelected = viewModel::onPeriodoSelected
                    )

                    Spacer(Modifier.height(24.dp))


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
                            imageVector = Icons.Default.List, // Puedes cambiar el ícono
                            contentDescription = "Icono de asignaturas",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }

            items(uiState.asignaturas) { asignatura ->
                AsignaturaCard(asignatura)
            }
        }
    }*/
}

