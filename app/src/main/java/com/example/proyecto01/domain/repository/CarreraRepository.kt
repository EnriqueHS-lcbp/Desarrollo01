package com.example.proyecto01.domain.repository

import com.example.proyecto01.domain.model.Carrera

interface CarreraRepository {

    suspend fun getCarrera(idEstud: Int): List<Carrera>
}