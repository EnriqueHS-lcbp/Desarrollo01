package com.example.proyecto01.presentation.asistencia.state

import com.example.proyecto01.data.remote.dto.AsignaturaDto
import com.example.proyecto01.data.remote.dto.CarreraDto
import com.example.proyecto01.data.remote.dto.PeriodoDto

data class AsistenciaUiState(
    val fotoUrl: String = "",
    val cursos: List<String> = emptyList(),
    val cursoSeleccionado: String = "",
    val carreras: List<CarreraDto> = emptyList(),
    val carreraSeleccionada: CarreraDto? = null, // String = "",
    val periodos: List<PeriodoDto> = emptyList(),
    val periodoSeleccionado: PeriodoDto? = null, // String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

data class AsignaturasUiState(
    val asignaturas: List<AsignaturaDto> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
