package com.example.proyecto01.presentation.home

import android.R.attr.text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(
    navController: NavController,
    isDarkTheme: MutableState<Boolean>,
) {

    var text by remember { mutableStateOf("18052") }

    Scaffold(

    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Codigo de estudiante") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Modo oscuro")
                Spacer(modifier = Modifier.width(8.dp))
                Switch(
                    checked = isDarkTheme.value,
                    onCheckedChange = { isDarkTheme.value = it }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { val id = text.toIntOrNull()
                    if (id != null) {
                        navController.navigate("asistencia/$id")
                    } },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Enviar")
            }
        }
    }
}

/*@Preview(showBackground = true)
@Composable
fun SimpleInputScreenPreview() {
    HomeScreen()
}*/