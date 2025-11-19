package com.example.proyecto01.domain.repository

import com.example.proyecto01.data.remote.model.AsignaturaAsisitenciaRequest
import com.example.proyecto01.data.remote.dto.AsignaturaAsistenciaDto
import com.example.proyecto01.data.remote.dto.AsignaturaDto
import com.example.proyecto01.data.remote.model.AsignaturaRequest
import com.example.proyecto01.data.remote.dto.CarreraDto
import com.example.proyecto01.data.remote.model.CarreraRequest
import com.example.proyecto01.data.remote.dto.PeriodoDto
import com.example.proyecto01.data.remote.model.PeriodoRequest

interface Repository {

    suspend fun getCarreras(request: CarreraRequest): List<CarreraDto>

    suspend fun getPeriodos(request: PeriodoRequest) : List<PeriodoDto>

    suspend fun getAsignaturas(request: AsignaturaRequest): List<AsignaturaDto>

    suspend fun getAsistenciaAsignatura(request: AsignaturaAsisitenciaRequest): List<AsignaturaAsistenciaDto>
}