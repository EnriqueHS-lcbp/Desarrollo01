package com.example.proyecto01.presentation.asistencia.state

import com.example.proyecto01.domain.model.Asignatura

data class AsistenciaUiState(
    val fotoUrl: String = "",
    val cursos: List<String> = emptyList(),
    val cursoSeleccionado: String = "",
    val carreras: List<String> = emptyList(),
    val carreraSeleccionada: String = "",
    val periodos: List<String> = emptyList(),
    val periodoSeleccionado: String = "",
    val asignaturas: List<Asignatura> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
