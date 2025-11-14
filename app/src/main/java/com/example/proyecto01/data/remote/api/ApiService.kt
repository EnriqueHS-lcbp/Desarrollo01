package com.example.proyecto01.data.remote.api

import com.example.proyecto01.data.remote.dto.AsignaturaRequestDto
import com.example.proyecto01.data.remote.dto.AsignaturasResponseDto
import com.example.proyecto01.data.remote.dto.CarreraRequestDto
import com.example.proyecto01.data.remote.dto.CarreraResponseDto
import com.example.proyecto01.data.remote.dto.PeriodoRequestDto
import com.example.proyecto01.data.remote.dto.PeriodoResponseDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.http.ContentType
import io.ktor.client.request.setBody
import io.ktor.http.contentType

class ApiService (
    private val client: HttpClient = HttpClientProvider.client,
    private val baseUrl: String = "http://74.249.92.43:8080/saa-rest/webresources/intranetSAA/"
) {

    suspend fun getCarreras(request: CarreraRequestDto): CarreraResponseDto {
        //val requestBody = CarreraRequestDto(id_estud = idEstud)
        return client.post("${baseUrl}estudianteCarrera") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }


    suspend fun getPeriodos(request: PeriodoRequestDto): PeriodoResponseDTO {
        //val body = PeriodoRequestDto(id_estud_serv = idEstudServ)
        return client.post("${baseUrl}estudiantePeriodo") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    suspend fun getAsignaturas(request: AsignaturaRequestDto): AsignaturasResponseDto {

        return client.post("${baseUrl}estudianteAsignatura") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

}