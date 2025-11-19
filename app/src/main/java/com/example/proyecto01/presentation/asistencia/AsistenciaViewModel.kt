package com.example.proyecto01.presentation.asistencia


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto01.data.remote.model.AsignaturaRequest
import com.example.proyecto01.data.remote.dto.CarreraDto
import com.example.proyecto01.data.remote.model.CarreraRequest
import com.example.proyecto01.data.remote.dto.PeriodoDto
import com.example.proyecto01.data.remote.model.PeriodoRequest
import com.example.proyecto01.domain.usecase.GeneralUseCase
import com.example.proyecto01.presentation.asistencia.state.AsignaturasUiState
import com.example.proyecto01.presentation.asistencia.state.AsistenciaUiState

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AsistenciaViewModel(
    //private val getCarreraUseCase: GetCarreraUseCase
    private val useCase: GeneralUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AsistenciaUiState())
    private val _asignaturasUiState = MutableStateFlow(AsignaturasUiState())

    val uiState: StateFlow<AsistenciaUiState> = _uiState
    val asignaturasUiState: StateFlow<AsignaturasUiState> = _asignaturasUiState

    /*init {
        //val request = CarreraRequestDto(id_estud = 2345)
        loadInitialData(request)
    }*/

    fun loadInitialData(request: CarreraRequest) {
        viewModelScope.launch {
            //val request = CarreraRequestDto(id_estud = 23197)
            cargarCarreras(request)

        }
    }

    fun onCarreraSelected(carrera: CarreraDto) {
        _uiState.value = _uiState.value.copy(carreraSeleccionada = carrera)
    }

    fun onPeriodoSelected(periodo: PeriodoDto) {
        _uiState.value = _uiState.value.copy(periodoSeleccionado = periodo)

        val estudianteId = _uiState.value.carreraSeleccionada?.id_estud_pe ?: 0
        val periodoId = periodo.id_peracad
        val request = AsignaturaRequest(
            id_estud_pe = estudianteId,
            id_peracad = periodoId
        )
        System.out.println(request)
        cargarAsignaturas(request)

    }

    fun cargarCarreras(request: CarreraRequest) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {

                val carrerasList = useCase.getCarreras(request)
                val carreraSeleccionada = carrerasList.firstOrNull()

                _uiState.value = _uiState.value.copy(
                    carreras = carrerasList,
                    carreraSeleccionada = carreraSeleccionada,
                    fotoUrl = carreraSeleccionada?.ped_url_imagen.orEmpty(),
                    isLoading = false,
                    error = null
                )

                carreraSeleccionada?.let {
                    val request = PeriodoRequest(id_estud_serv = it.id_estud_serv)
                    cargarPeriodos(request)
                }

                //carreraSeleccionada?.idEstudServ?.let { cargarPeriodos(it) }

            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = "Error al cargar carreras: ${e.message}",
                    isLoading = false
                )
            }
        }
    }

    fun cargarPeriodos(request: PeriodoRequest) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val periodosList = useCase.getPeriodos(request)
                val periodoSeleccionado = periodosList.firstOrNull()

                _uiState.value = _uiState.value.copy(
                    periodos = periodosList,
                    periodoSeleccionado = periodoSeleccionado,
                    isLoading = false,
                    error = null
                )

                periodoSeleccionado?.let{
                    val dd = it.id_estud_pe.toInt()
                    val request = AsignaturaRequest(
                        id_estud_pe = dd ,
                        id_peracad = it.id_peracad
                    )
                    cargarAsignaturas(request)
                }
                /*periodoSeleccionado?.peracad_id?.let {
                    cargarAsignaturas(estudianteId = 23197, periodoId = it)
                }*/

            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = "Error al cargar periodos: ${e.message}",
                    isLoading = false
                )
            }
        }
    }

    fun cargarAsignaturas(request: AsignaturaRequest) {
        System.out.println("AQUI EN CARGAR ASIGNATURAS")
        viewModelScope.launch {
            _asignaturasUiState.value = _asignaturasUiState.value.copy(isLoading = true)
            try {
               val response = useCase.getAsignaturas(request)

                _asignaturasUiState.value = _asignaturasUiState.value.copy(
                    asignaturas = response,
                    isLoading = false,
                    error = null
                )
            } catch (e: Exception) {
                _asignaturasUiState.value = _asignaturasUiState.value.copy(
                    error = "Error al cargar asignaturas: ${e.message}",
                    isLoading = false
                )
            }
        }
    }
}
