package com.example.proyecto01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.proyecto01.data.remote.api.ApiService
import com.example.proyecto01.data.repository.RepositoryImpl
import com.example.proyecto01.domain.usecase.GeneralUseCase
import com.example.proyecto01.presentation.asistencia.AsistenciaViewModel
import com.example.proyecto01.presentation.asistenciaAsignatura.AsignaturaAsistenciaViewModel
import com.example.proyecto01.presentation.navigation.AppNavigation
import com.example.proyecto01.ui.theme.Proyecto01Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val isDarkTheme = remember { mutableStateOf(false) }
            Crossfade(targetState = isDarkTheme.value) { dark ->
                Proyecto01Theme(darkTheme = dark) {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        Box(modifier = Modifier.padding(innerPadding)) {

                            val apiService = ApiService()
                            val generalRepository = RepositoryImpl(apiService)
                            val generalUseCase = GeneralUseCase(generalRepository)

                            val asistenciaViewModel = remember {
                                AsistenciaViewModel(generalUseCase)
                            }

                            val asignaturaAsistenciaViewModel = remember {
                                AsignaturaAsistenciaViewModel(generalRepository)
                            }

                            //val idEstudiante: Int = 18052 //23197
                            //AsistenciaScreen(idEstudiante,viewModel = asistenciaViewModel)
                            AppNavigation(
                                isDarkTheme = isDarkTheme,
                                //idEstudiante= idEstudiante,
                                asistenciaViewModel = asistenciaViewModel,
                                asignaturaAsistenciaViewModel = asignaturaAsistenciaViewModel
                            )
                        }
                    }
                }
            }
        }
    }
}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Proyecto01Theme {
        Greeting("Android")
    }
}