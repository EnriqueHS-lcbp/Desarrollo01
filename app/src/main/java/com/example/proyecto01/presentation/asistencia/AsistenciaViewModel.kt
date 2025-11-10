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

class AsistenciaViewModel(
    private val getCarreraUseCase: GetCarreraUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AsistenciaUiState())
    val uiState: StateFlow<AsistenciaUiState> = _uiState

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val carrerasList = getCarreraUseCase(23197)

                _uiState.value = _uiState.value.copy(
                    carreras = carrerasList.map { it.servNombre },
                    carreraSeleccionada = carrerasList.firstOrNull()?.servNombre.orEmpty(),
                    fotoUrl = carrerasList.firstOrNull()?.pedUrlImagen.orEmpty(),
                    isLoading = false,
                    error = null
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = "Error al cargar carreras: ${e.message}",
                    isLoading = false
                )
            }
        }
    }

    fun onCarreraSelected(carrera: String) {
        _uiState.value = _uiState.value.copy(carreraSeleccionada = carrera)
    }

    fun onPeriodoSelected(periodo: String) {
        _uiState.value = _uiState.value.copy(periodoSeleccionado = periodo)
    }
}
