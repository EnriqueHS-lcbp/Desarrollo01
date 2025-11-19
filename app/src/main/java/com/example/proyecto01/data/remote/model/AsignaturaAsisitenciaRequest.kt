package com.example.proyecto01.data.remote.model

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class AsignaturaAsisitenciaRequest(
    val id_estud_pe: Int,
    val id_matric_asig_secc: Int
)