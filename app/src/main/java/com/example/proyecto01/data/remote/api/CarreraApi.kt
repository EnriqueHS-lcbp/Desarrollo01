package com.example.proyecto01.data.remote.api


import com.example.proyecto01.data.remote.dto.CarreraResponseDto
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class CarreraApi {

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun getCarreras(): CarreraResponseDto {
        val response: HttpResponse = client.get("http://74.249.92.43:8080/saa-rest/webresources/intranetSAA/estudianteCarrera")
        return response.body()
    }

}