package com.example.proyecto01.domain.usecase

import com.example.proyecto01.data.remote.dto.AsignaturaAsisitenciaRequestDto
import com.example.proyecto01.data.remote.dto.AsignaturaAsistenciaDto
import com.example.proyecto01.data.remote.dto.AsignaturaAsistenciaResponseDto
import com.example.proyecto01.data.remote.dto.AsignaturaDto
import com.example.proyecto01.data.remote.dto.AsignaturaRequestDto
import com.example.proyecto01.data.remote.dto.CarreraDto
import com.example.proyecto01.data.remote.dto.CarreraRequestDto
import com.example.proyecto01.data.remote.dto.PeriodoDto
import com.example.proyecto01.data.remote.dto.PeriodoRequestDto
import com.example.proyecto01.domain.model.Carrera
import com.example.proyecto01.domain.repository.GeneralRepository

class GeneralUseCase(
    private val repository: GeneralRepository
) {

    suspend fun getCarreras(request: CarreraRequestDto): List<CarreraDto> {
        return repository.getCarreras(request)
    }

    suspend fun getPeriodos(request: PeriodoRequestDto): List<PeriodoDto> {
        return repository.getPeriodos(request)
    }

    suspend fun getAsignaturas(request: AsignaturaRequestDto): List<AsignaturaDto>{
        return repository.getAsignaturas(request)
    }

    suspend fun getAsignaturaAsistencia(request: AsignaturaAsisitenciaRequestDto): List<AsignaturaAsistenciaDto>{
        return repository.getAsistenciaAsignatura(request)
    }
}