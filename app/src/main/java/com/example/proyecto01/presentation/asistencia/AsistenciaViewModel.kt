package com.example.proyecto01.presentation.asistencia

import com.example.proyecto01.domain.model.Asignatura


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto01.presentation.asistencia.state.AsistenciaUiState

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AsistenciaViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AsistenciaUiState())
    val uiState: StateFlow<AsistenciaUiState> = _uiState

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            // Simulación de datos iniciales
            val cursos = listOf("1° Primaria", "2° Primaria", "3° Primaria")
            val periodos = listOf("2025-I", "2025-II", "2026-I")
            val asignaturas = listOf(
                Asignatura("Matemáticas", "Prof. García"),
                Asignatura("Ciencias", "Prof. López"),
                Asignatura("Lenguaje", "Prof. Torres"),
                Asignatura("Arte", "Prof. Díaz")
            )

            _uiState.value = _uiState.value.copy(
                fotoUrl = "https://example.com/foto_alumno.jpg",
                cursos = cursos,
                cursoSeleccionado = cursos.first(),
                periodos = periodos,
                periodoSeleccionado = periodos.first(),
                asignaturas = asignaturas
            )
        }
    }

    fun onCursoSelected(curso: String) {
        _uiState.value = _uiState.value.copy(cursoSeleccionado = curso)
    }

    fun onPeriodoSelected(periodo: String) {
        _uiState.value = _uiState.value.copy(periodoSeleccionado = periodo)
    }
}
