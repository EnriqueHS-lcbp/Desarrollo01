package com.example.proyecto01.data.remote.dto

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class AsignaturaAsistenciaDto(
    val hor_fin: String,
    val id_hor_asis : String,
    val sesion: String,
    val fecha_dia: String,
    val hor_asis_dia: String,
    val docente: String,
    val asistio: String,
    val hor_inicio: String,
    val dia: String,
    val clase: String
)

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class AsignaturaAsistenciaResponseDto(
    val flag_val: Int,
    val listadoCarrera: List<AsignaturaAsistenciaDto>
)
