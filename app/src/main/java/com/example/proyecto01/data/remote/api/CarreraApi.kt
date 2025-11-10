package com.example.proyecto01.data.remote.api


import com.example.proyecto01.data.remote.dto.CarreraRequestDto
import com.example.proyecto01.data.remote.dto.CarreraResponseDto
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import io.ktor.http.ContentType
import io.ktor.http.contentType

class CarreraApi {

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun getCarreras(idEstud: Int): CarreraResponseDto {
        val requestBody = CarreraRequestDto(id_estud = idEstud)
        return client.post("http://74.249.92.43:8080/saa-rest/webresources/intranetSAA/estudianteCarrera") {
            contentType(ContentType.Application.Json)
            setBody(requestBody)
        }.body()
    }
}