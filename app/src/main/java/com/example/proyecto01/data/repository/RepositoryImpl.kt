package com.example.proyecto01.data.repository

import com.example.proyecto01.data.remote.api.ApiService
import com.example.proyecto01.data.remote.model.AsignaturaAsisitenciaRequest
import com.example.proyecto01.data.remote.dto.AsignaturaDto
import com.example.proyecto01.data.remote.model.AsignaturaRequest
import com.example.proyecto01.data.remote.dto.AsignaturaAsistenciaDto
import com.example.proyecto01.data.remote.dto.CarreraDto
import com.example.proyecto01.data.remote.model.CarreraRequest
import com.example.proyecto01.data.remote.dto.PeriodoDto
import com.example.proyecto01.data.remote.model.PeriodoRequest
import com.example.proyecto01.domain.repository.Repository

class RepositoryImpl (
    private val api: ApiService
)  : Repository {
    override suspend fun getCarreras(request: CarreraRequest): List<CarreraDto> {
        val response = api.getCarreras(request)
        println("CarreraReositoryImpl:{} "+ response)
        return response.carrera ?: emptyList()
        //return lista.map { it.toDomain() }
    }

    override suspend fun getPeriodos(request: PeriodoRequest): List<PeriodoDto> {
        val response = api.getPeriodos(request)
        println("GeneralRepositoryImpl:{} "+ response)
        return response.periodo ?: emptyList()
    }

    override suspend fun getAsignaturas(request: AsignaturaRequest): List<AsignaturaDto> {
        val response = api.getAsignaturas(request)
        println("GeneralRepositoryImpl:{} "+ response)
        return response.asignatura ?: emptyList()
    }

    override suspend fun getAsistenciaAsignatura(request: AsignaturaAsisitenciaRequest): List<AsignaturaAsistenciaDto> {
        val response = api.getAsignaturaAsistencia(request)
        println("GeneralRepositoryImpl:{} "+ response)
        return response.listadoCarrera ?: emptyList()
    }


}