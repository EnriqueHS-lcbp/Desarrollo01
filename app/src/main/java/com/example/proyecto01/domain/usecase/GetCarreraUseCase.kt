package com.example.proyecto01.domain.usecase

import com.example.proyecto01.domain.model.Carrera
import com.example.proyecto01.domain.repository.CarreraRepository

class GetCarreraUseCase(
    private val repository: CarreraRepository
) {
    suspend operator fun invoke(idEstud: Int): List<Carrera> {
        return repository.getCarrera(idEstud)
    }
}