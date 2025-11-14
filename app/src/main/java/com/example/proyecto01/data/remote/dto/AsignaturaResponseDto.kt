package com.example.proyecto01.data.remote.dto

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class AsignaturaDto(
    val pest_det_asis_min: String,
    val id_matric: String,
    val pest_asign_nombre: String,
    val id_matric_asig_secc: String,
    val id_pest_det_asign: String,
    val id_oad_seccion : String,
    val total_max_inas: String,
    val matric_asig_porc_inasistencia: String,
    val id_modulod_pestd: String,
    val id_oacad_arranque: String,
    val id_hor: String,
    val peda_url_imagen: String
)

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class AsignaturasResponseDto (
    val flag_val: Int,
    val asignatura: List<AsignaturaDto>?= null
)