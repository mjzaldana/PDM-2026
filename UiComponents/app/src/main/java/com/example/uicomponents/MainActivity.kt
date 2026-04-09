package com.example.uicomponents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         setContent {

        }
    }
}

@Preview
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun Screen1(){

    var nombre by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passVisible by remember { mutableStateOf(false) }
    var switchOn by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text("App 1")},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Blue,
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            SectionCard("TextField") {
                OutlinedTextField(
                    value = nombre,
                    onValueChange = {nombre = it},
                    label = { Text("Nombre")},
                    leadingIcon = {
                        Icon(Icons.Default.Person,
                            "Icono de persona")
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(16.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = {password = it},
                    label = { Text("Password")},
                    leadingIcon = {
                        Icon(Icons.Default.Lock,
                            "Icono de Candado")
                    },
                    trailingIcon = {
                        IconButton(onClick = { passVisible = !passVisible} ) {
                            Icon(
                                if(passVisible) Icons.Default.Clear
                                else Icons.Default.Done,
                                "Icono de mostrar password"
                            )
                        }
                    },
                    visualTransformation =
                        if (passVisible) VisualTransformation.None
                        else PasswordVisualTransformation(),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            SectionCard(
                "Switch"
            ) {
                Text(if(switchOn) "Modo Oscuro" else "Modo claro")
                Switch(
                    checked = switchOn,
                    onCheckedChange = { switchOn = it }
                )
            }
        }
    }
}

@Composable
fun SectionCard(title: String, content: @Composable ColumnScope.() -> Unit){
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ){
            Text(
                text = title,
                fontWeight = FontWeight.Bold
            )
            HorizontalDivider(
                modifier = Modifier.padding(8.dp)
            )
            content()
        }
    }
}
