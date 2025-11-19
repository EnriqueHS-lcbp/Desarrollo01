package com.example.proyecto01.data.remote.model

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class AsignaturaRequest(
    val id_estud_pe: Int,
    val id_peracad: String
)