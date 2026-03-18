package com.example.test1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.test1.ui.theme.Test1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            userInputList()
        }
    }
}

@Composable
fun Saludo(name: String) {
    Column{
        Column{
            Text(
                "Hola $name",
                modifier = Modifier
                    .padding(16.dp)
                    .background(Color.Red)
            )
            Text(
                "Como estas",
                modifier = Modifier
                    .padding(16.dp)
                    .background(Color.Red)
            )
        }
        Row{
            Text(
                "Hola $name",
                modifier = Modifier
                    .padding(16.dp)
                    .background(Color.Red)
            )
            Text(
                "Como estas",
                modifier = Modifier
                    .padding(16.dp)
                    .background(Color.Red)
            )
        }
        Box {
            Text("Hola")
        }
    }
}

@Composable
fun userCard(name: String){
    Card(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = name,
            modifier = Modifier
                .padding(16.dp)
        )
    }
}
@Composable
fun userCardLayout(){
    Column{
        userCard("Mauricio")
        userCard("Jose")
        userCard("UCA")
    }
}

@Composable
fun Contador(){
    var contador by remember { mutableStateOf(0) }
    Column {
        Text("Valor: $contador")
        Button(
            onClick = {contador++}
        ){
            Text("Sumar 1")
        }
    }
}

@Composable
fun userList(){
    val usuarios by remember { mutableStateOf(listOf<String>()) }

    LazyColumn() {
        items(usuarios){u ->
            Text(
                text = u,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun userInput(){
    var texto by remember { mutableStateOf("") }

    Column(

    ) {
        TextField(
            value = texto,
            onValueChange = { texto = it},
            label = { Text("Escribe algo")},
            modifier = Modifier.padding(16.dp)
        )
        TextField(
            value = texto,
            onValueChange = { texto = it},
            label = { Text("Escribe Tu nombre")},
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun userInputList(){
    var text by remember {
        mutableStateOf("")
    }
    var list by remember {
        mutableStateOf(listOf<String>())
    }

    Column(
        modifier = Modifier.padding(20.dp)
    ) {
        TextField(
            value = text,
            onValueChange = {text = it}
        )
        Button(
            onClick = {
                list = list + text
                text = ""
            }
        ) {
            Text("Agregar")
        }
        LazyColumn() {
            items(list){
                Text(it)
            }
        }
    }
}

@Preview
@Composable
fun mainPreview(){
    userInputList()
}