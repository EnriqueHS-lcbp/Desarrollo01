package com.example.proyecto01.data.remote.mapper

import com.example.proyecto01.data.remote.dto.CarreraDto
import com.example.proyecto01.domain.model.Carrera

fun CarreraDto.toDomain(): Carrera {
    return Carrera(
        pedUrlImagen = pedUrlImagen,
        idEstudPe = idEstudPe,
        idPestDet = idPestDet,
        idServ = idServ,
        flagCarrera = flagCarrera,
        idEstudServ = idEstudServ,
        idTipoServa = idTipoServa,
        idUneg = idUneg,
        servNombre = servNombre,
        idEstud = idEstud
    )
}