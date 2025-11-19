package com.example.proyecto01.presentation.asistenciaAsignatura

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto01.data.remote.model.AsignaturaAsisitenciaRequest
import com.example.proyecto01.domain.repository.Repository
import com.example.proyecto01.presentation.asistenciaAsignatura.state.AsignaturaAsistenciaUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AsignaturaAsistenciaViewModel (
    private val repository: Repository
): ViewModel(){

    private val _asignaturasAsistenciaUiState = MutableStateFlow(AsignaturaAsistenciaUiState())

    val asignaturasAsistenciaUiState: StateFlow<AsignaturaAsistenciaUiState> = _asignaturasAsistenciaUiState

    fun loadInitialData(request: AsignaturaAsisitenciaRequest) {
        viewModelScope.launch {
            cargarAsistencias(request)
        }
    }

    fun cargarAsistencias(request: AsignaturaAsisitenciaRequest) {
        viewModelScope.launch {
            _asignaturasAsistenciaUiState.value = _asignaturasAsistenciaUiState.value.copy(isLoading = true)
            try {
                Log.d("idEstudiante", "${request.id_estud_pe}")
                Log.d("idEstudiante", "${request.id_matric_asig_secc}")
                val asistenciasList = repository.getAsistenciaAsignatura(request)

                Log.d("Listado de Asistencia", "${asistenciasList}" )

                _asignaturasAsistenciaUiState.value = _asignaturasAsistenciaUiState.value.copy(
                    asistenciasAsignatura = asistenciasList,
                    isLoading = false,
                    error = null
                )


            } catch (e: Exception) {
                _asignaturasAsistenciaUiState.value = _asignaturasAsistenciaUiState.value.copy(
                    error = "Error al cargar carreras: ${e.message}",
                    isLoading = false
                )
            }
        }
    }

    fun onTabSelected(index: Int) {
        println("EN TAB INDEX:"+ index)
        _asignaturasAsistenciaUiState.value = _asignaturasAsistenciaUiState.value.copy(tabIndex = index)
    }
}