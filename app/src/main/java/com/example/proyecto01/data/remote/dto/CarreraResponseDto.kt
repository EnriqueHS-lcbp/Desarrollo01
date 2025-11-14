package com.example.proyecto01.data.remote.dto


import android.annotation.SuppressLint
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class CarreraDto(
    val ped_url_imagen: String?,
    val id_estud_pe: Int,
    val id_pest_det: String,
    val id_serv: String,
    val flag_carrera: String,
    val id_estud_serv: String,
    val id_tiposerva: String,
    val id_uneg: String,
    val serv_nombre: String,
    val id_estud: String

);

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class CarreraResponseDto(
    val flag_val: Int,
    val carrera: List<CarreraDto>?= null
)
