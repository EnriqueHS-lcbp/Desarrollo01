package com.example.proyecto01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.proyecto01.data.remote.api.ApiService
import com.example.proyecto01.data.remote.api.CarreraApi
import com.example.proyecto01.data.repository.CarreraRepositoryImpl
import com.example.proyecto01.data.repository.GeneralRepositoryImpl
import com.example.proyecto01.domain.repository.GeneralRepository
import com.example.proyecto01.domain.usecase.GeneralUseCase
import com.example.proyecto01.domain.usecase.GetCarreraUseCase
import com.example.proyecto01.presentation.asistencia.AsistenciaScreen
import com.example.proyecto01.presentation.asistencia.AsistenciaViewModel
import com.example.proyecto01.presentation.navigation.AppNavigation
import com.example.proyecto01.ui.theme.Proyecto01Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Proyecto01Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        /*val carreraApi = CarreraApi()
                        val carreraRepository = CarreraRepositoryImpl(carreraApi)
                        val getCarreraUseCase = GetCarreraUseCase(carreraRepository)
*/
                        val apiService = ApiService()
                        val generalRepository = GeneralRepositoryImpl(apiService)
                        val generalUseCase = GeneralUseCase(generalRepository)

                        val asistenciaViewModel = remember {
                            //AsistenciaViewModel(getCarreraUseCase)
                            AsistenciaViewModel(generalUseCase)
                        }

                        val idEstudiante: Int = 23197
                        //AsistenciaScreen(idEstudiante,viewModel = asistenciaViewModel)
                        AppNavigation(
                            idEstudiante= idEstudiante,
                            asistenciaViewModel = asistenciaViewModel
                        )
                    //AsistenciaScreen()
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