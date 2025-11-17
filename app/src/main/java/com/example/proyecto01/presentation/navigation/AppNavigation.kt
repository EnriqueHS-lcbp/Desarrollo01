package com.example.proyecto01.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyecto01.presentation.asistencia.AsistenciaScreen
import com.example.proyecto01.presentation.asistencia.AsistenciaViewModel

@Composable
fun AppNavigation(
    idEstudiante: Int,
    asistenciaViewModel: AsistenciaViewModel
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "asistencia") {
        composable("asistencia") {
            // Aquí se pasa el ID al screen
            AsistenciaScreen(
                idEstudiante = idEstudiante,
                viewModel = asistenciaViewModel,
                navController = navController
            )
        }
        /*composable("detalle_asistencia/{asignaturaId}") { backStackEntry ->
            val asignaturaId = backStackEntry.arguments?.getString("asignaturaId")
            DetalleAsistenciaScreen(asignaturaId = asignaturaId)
        }*/
    }
}