package com.example.proyecto01.data.remote.model

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class CarreraRequest(
    val id_estud: Int
)