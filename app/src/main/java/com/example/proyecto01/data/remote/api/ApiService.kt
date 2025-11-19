package com.example.proyecto01.data.remote.api

import com.example.proyecto01.data.remote.model.AsignaturaAsisitenciaRequest
import com.example.proyecto01.data.remote.dto.AsignaturaAsistenciaResponseDto
import com.example.proyecto01.data.remote.model.AsignaturaRequest
import com.example.proyecto01.data.remote.dto.AsignaturasResponseDto
import com.example.proyecto01.data.remote.model.CarreraRequest
import com.example.proyecto01.data.remote.dto.CarreraResponseDto
import com.example.proyecto01.data.remote.model.PeriodoRequest
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

    suspend fun getCarreras(request: CarreraRequest): CarreraResponseDto {
        //val requestBody = CarreraRequestDto(id_estud = idEstud)
        return client.post("${baseUrl}estudianteCarrera") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }


    suspend fun getPeriodos(request: PeriodoRequest): PeriodoResponseDTO {
        //val body = PeriodoRequestDto(id_estud_serv = idEstudServ)
        return client.post("${baseUrl}estudiantePeriodo") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    suspend fun getAsignaturas(request: AsignaturaRequest): AsignaturasResponseDto {

        return client.post("${baseUrl}estudianteAsignatura") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    /** obtiene el horario de asistencia a las asignaturas **/
    suspend fun getAsignaturaAsistencia(request: AsignaturaAsisitenciaRequest): AsignaturaAsistenciaResponseDto {

        return client.post("${baseUrl}estudianteListadoCarrera") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

}