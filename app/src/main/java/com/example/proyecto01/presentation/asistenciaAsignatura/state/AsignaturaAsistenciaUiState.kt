package com.example.proyecto01.presentation.asistenciaAsignatura.state

import com.example.proyecto01.data.remote.dto.AsignaturaAsistenciaDto
import com.example.proyecto01.data.remote.dto.CarreraDto
import kotlin.collections.List

data class AsignaturaAsistenciaUiState (

    val asistenciasAsignatura : List<AsignaturaAsistenciaDto> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val tabIndex: Int = 0
)