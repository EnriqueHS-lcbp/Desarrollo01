package com.example.proyecto01.data.repository

import android.util.Log
import com.example.proyecto01.data.remote.api.CarreraApi
import com.example.proyecto01.data.remote.mapper.toDomain
import com.example.proyecto01.domain.model.Carrera
import com.example.proyecto01.domain.repository.CarreraRepository


class CarreraRepositoryImpl (
    private val api: CarreraApi
) : CarreraRepository {
        override suspend fun getCarrera(idEstud: Int): List<Carrera> {
            val response = api.getCarreras(idEstud)
            println("CarreraReositoryImpl:{} "+ response)
            val lista = response.carrera ?: emptyList()
            return  emptyList() // lista.map { it.toDomain() }
            /*return response.carrera.map {
                it.toDomain()
            }*/
        }

}