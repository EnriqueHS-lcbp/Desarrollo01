package com.example.proyecto01.data.remote.dto

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class CarreraRequestDto(
    val id_estud: Int
)
