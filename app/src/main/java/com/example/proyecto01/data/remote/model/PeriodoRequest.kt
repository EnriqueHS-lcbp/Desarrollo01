package com.example.proyecto01.data.remote.model

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class PeriodoRequest(
    val id_estud_serv: String
)