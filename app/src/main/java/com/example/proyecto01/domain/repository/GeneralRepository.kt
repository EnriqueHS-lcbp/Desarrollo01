package com.example.proyecto01.domain.repository

import com.example.proyecto01.data.remote.dto.AsignaturaAsisitenciaRequestDto
import com.example.proyecto01.data.remote.dto.AsignaturaAsistenciaDto
import com.example.proyecto01.data.remote.dto.AsignaturaDto
import com.example.proyecto01.data.remote.dto.AsignaturaRequestDto
import com.example.proyecto01.data.remote.dto.CarreraDto
import com.example.proyecto01.data.remote.dto.CarreraRequestDto
import com.example.proyecto01.data.remote.dto.PeriodoDto
import com.example.proyecto01.data.remote.dto.PeriodoRequestDto
import com.example.proyecto01.domain.model.Carrera

interface GeneralRepository {

    suspend fun getCarreras(request: CarreraRequestDto): List<CarreraDto>

    suspend fun getPeriodos(request: PeriodoRequestDto) : List<PeriodoDto>

    suspend fun getAsignaturas(request: AsignaturaRequestDto): List<AsignaturaDto>

    suspend fun getAsistenciaAsignatura(request: AsignaturaAsisitenciaRequestDto): List<AsignaturaAsistenciaDto>
}