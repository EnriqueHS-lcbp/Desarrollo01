package com.example.proyecto01.data.repository

import com.example.proyecto01.data.remote.api.ApiService
import com.example.proyecto01.data.remote.dto.AsignaturaDto
import com.example.proyecto01.data.remote.dto.AsignaturaRequestDto
import com.example.proyecto01.data.remote.dto.CarreraDto
import com.example.proyecto01.data.remote.dto.CarreraRequestDto
import com.example.proyecto01.data.remote.dto.PeriodoDto
import com.example.proyecto01.data.remote.dto.PeriodoRequestDto
import com.example.proyecto01.data.remote.mapper.toDomain
import com.example.proyecto01.domain.model.Carrera
import com.example.proyecto01.domain.repository.GeneralRepository

class GeneralRepositoryImpl (
    private val api: ApiService
)  : GeneralRepository {
    override suspend fun getCarreras(request: CarreraRequestDto): List<CarreraDto> {
        val response = api.getCarreras(request)
        println("CarreraReositoryImpl:{} "+ response)
        return response.carrera ?: emptyList()
        //return lista.map { it.toDomain() }
    }

    override suspend fun getPeriodos(request: PeriodoRequestDto): List<PeriodoDto> {
        val response = api.getPeriodos(request)
        println("GeneralRepositoryImpl:{} "+ response)
        return response.periodo ?: emptyList()
    }

    override suspend fun getAsignaturas(request: AsignaturaRequestDto): List<AsignaturaDto> {
        val response = api.getAsignaturas(request)
        println("GeneralRepositoryImpl:{} "+ response)
        return response.asignatura ?: emptyList()
    }
}