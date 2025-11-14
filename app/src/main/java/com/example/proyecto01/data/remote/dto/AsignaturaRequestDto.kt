package com.example.proyecto01.data.remote.dto

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class AsignaturaRequestDto(
    val id_estud_pe: Int,
    val id_peracad: String
)
