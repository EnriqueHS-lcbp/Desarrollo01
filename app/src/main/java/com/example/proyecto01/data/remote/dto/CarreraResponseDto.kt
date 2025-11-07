package com.example.proyecto01.data.remote.dto


import android.annotation.SuppressLint
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class CarreraDto(
    @SerialName("ped_url_imagen") val pedUrlImagen: String?,
    @SerialName("id_estud_pe") val idEstudPe: Int,
    @SerialName("id_pest_det") val idPestDet: String,
    @SerialName("id_serv") val idServ: String,
    @SerialName("flag_carrera") val flagCarrera: String,
    @SerialName("id_estud_serv") val idEstudServ: String,
    @SerialName("id_tiposerva") val idTipoServa: String,
    @SerialName("id_uneg") val idUneg: String,
    @SerialName("serv_nombre") val servNombre: String,
    @SerialName("id_estud") val idEstud: String

);

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class CarreraResponseDto(
    @SerialName("flag_val") val flagVal: Int,
    @SerialName("carrera") val carrera: List<CarreraDto>?= null
)
