package com.example.proyecto01.domain.usecase

import com.example.proyecto01.data.remote.model.AsignaturaAsisitenciaRequest
import com.example.proyecto01.data.remote.dto.AsignaturaAsistenciaDto
import com.example.proyecto01.data.remote.dto.AsignaturaDto
import com.example.proyecto01.data.remote.model.AsignaturaRequest
import com.example.proyecto01.data.remote.dto.CarreraDto
import com.example.proyecto01.data.remote.model.CarreraRequest
import com.example.proyecto01.data.remote.dto.PeriodoDto
import com.example.proyecto01.data.remote.model.PeriodoRequest
import com.example.proyecto01.domain.repository.Repository

class GeneralUseCase(
    private val repository: Repository
) {

    suspend fun getCarreras(request: CarreraRequest): List<CarreraDto> {
        return repository.getCarreras(request)
    }

    suspend fun getPeriodos(request: PeriodoRequest): List<PeriodoDto> {
        return repository.getPeriodos(request)
    }

    suspend fun getAsignaturas(request: AsignaturaRequest): List<AsignaturaDto>{
        return repository.getAsignaturas(request)
    }

    suspend fun getAsignaturaAsistencia(request: AsignaturaAsisitenciaRequest): List<AsignaturaAsistenciaDto>{
        return repository.getAsistenciaAsignatura(request)
    }
}