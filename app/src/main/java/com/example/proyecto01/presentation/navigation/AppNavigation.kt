package com.example.proyecto01.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.proyecto01.data.remote.model.AsignaturaAsisitenciaRequest
import com.example.proyecto01.data.remote.dto.AsignaturaDto
import com.example.proyecto01.presentation.asistencia.AsistenciaScreen
import com.example.proyecto01.presentation.asistencia.AsistenciaViewModel
import com.example.proyecto01.presentation.asistenciaAsignatura.AsignaturaAsistenciaScreen
import com.example.proyecto01.presentation.asistenciaAsignatura.AsignaturaAsistenciaViewModel
import com.example.proyecto01.presentation.home.HomeScreen

@Composable
fun AppNavigation(
    isDarkTheme : MutableState<Boolean>,
    //idEstudiante: Int,
    asistenciaViewModel: AsistenciaViewModel,
    asignaturaAsistenciaViewModel: AsignaturaAsistenciaViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home"){
            HomeScreen(navController = navController, isDarkTheme)
        }

        composable(
            route = "asistencia/{estudianteId}",
            arguments = listOf(
                navArgument("estudianteId"){type = NavType.IntType}
            )
        ) { backStackEntry ->
            val estudianteId = backStackEntry.arguments?.getInt("estudianteId") ?: return@composable

            AsistenciaScreen(
                idEstudiante = estudianteId, //idEstudiante,
                viewModel = asistenciaViewModel,
                navController = navController
            )
        }

        composable(
            route = "detalle_asistencia/{estudianteId}/{asignaturaId}/{asigNombre}/{inasisMax}/{inasisTotal}",
            arguments = listOf(
                navArgument("estudianteId") { type = NavType.IntType },
                navArgument("asignaturaId") { type = NavType.IntType },
                navArgument("asigNombre") { type = NavType.StringType },
                navArgument("inasisTotal") { type = NavType.StringType },
                navArgument("inasisMax") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val estudianteId = backStackEntry.arguments?.getInt("estudianteId") ?: return@composable
            val asignaturaId = backStackEntry.arguments?.getInt("asignaturaId") ?: return@composable
            val asigNombre = backStackEntry.arguments?.getString("asigNombre") ?: return@composable
            val inasisTotal = backStackEntry.arguments?.getString("inasisMax") ?: return@composable
            val inasisMax = backStackEntry.arguments?.getString("inasisTotal") ?: return@composable

            AsignaturaAsistenciaScreen(
                idEstudiante = estudianteId,
                idAsignatura = asignaturaId,
                asigNombre= asigNombre,
                inasisMax=inasisMax,
                inasisTotal= inasisTotal,
                viewModel = asignaturaAsistenciaViewModel,
                navController = navController
            )
        }


    }
}