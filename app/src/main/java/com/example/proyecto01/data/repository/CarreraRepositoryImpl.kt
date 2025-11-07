package com.example.proyecto01.data.repository

import com.example.proyecto01.data.remote.api.CarreraApi
import com.example.proyecto01.data.remote.mapper.toDomain
import com.example.proyecto01.domain.model.Carrera
import com.example.proyecto01.domain.repository.CarreraRepository


class CarreraRepositoryImpl (
    private val api: CarreraApi
) : CarreraRepository {
        override suspend fun getCarrera(): List<Carrera> {
            val response = api.getCarreras()
            return response.carrera.map {
                it.toDomain()
            }
        }

}