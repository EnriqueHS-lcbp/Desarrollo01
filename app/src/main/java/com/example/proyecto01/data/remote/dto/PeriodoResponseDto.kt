package com.example.proyecto01.data.remote.dto

import android.annotation.SuppressLint
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class PeriodoDto(
    val id_matric: String,
    val id_estud_pe: String,
    val id_pest_det: String,
    val id_oacad_arranque: String,
    val peracad_nombre: String,
    val id_peracad: String
)

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class PeriodoResponseDTO(
    val flag_val: Int,
    val periodo: List<PeriodoDto>?= null
)
