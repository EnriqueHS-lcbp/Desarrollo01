package com.example.proyecto01.presentation.asistencia

import android.util.Log
import com.example.proyecto01.domain.model.Asignatura


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto01.domain.usecase.GetCarreraUseCase
import com.example.proyecto01.presentation.asistencia.state.AsistenciaUiState

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AsistenciaViewModel (
    private val getCarreraUseCase: GetCarreraUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(AsistenciaUiState())
    val uiState: StateFlow<AsistenciaUiState> = _uiState

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        Log.d("ViewModel","Entro a ViewModel")
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            Log.d("ViewModel","Entro a ViewModel antes de try-catch")
            try {
                Log.d("ViewModel","Entro a ViewModel en try")
                val carreras = getCarreraUseCase(23197).map { it.servNombre }
                Log.d("AsistenciaViewModel", "Carreras: $carreras")
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
                    carreras = carreras,
                    carreraSeleccionada = carreras.firstOrNull() ?: "",
                    periodos = periodos,
                    periodoSeleccionado = periodos.first(),
                    asignaturas = asignaturas,
                    isLoading = false,
                    error = null
                )
            }catch (e: Exception) {
                Log.d("ViewModel","Entro a ViewModel en catch",e)
                _uiState.value = _uiState.value.copy(
                    error = "Error al cargar carreras",
                    isLoading = false
                )
            }
        }
    }

    fun onCarreraSelected(carrera: String){
        _uiState.value = _uiState.value.copy(carreraSeleccionada = carrera)
    }
    fun onCursoSelected(curso: String) {
        _uiState.value = _uiState.value.copy(cursoSeleccionado = curso)
    }

    fun onPeriodoSelected(periodo: String) {
        _uiState.value = _uiState.value.copy(periodoSeleccionado = periodo)
    }
}
