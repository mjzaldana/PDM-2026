package com.example.lifecycle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.lifecycle.ui.theme.LifecycleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VendingMachine()
        }
    }
}

@Preview
@Composable
fun VendingMachine(){
    var balance by remember {
        mutableStateOf(0.0)
    }
    var message by remember {
        mutableStateOf("Inserta Dinero")
    }

    val products = listOf(
        "Soda" to 1.25,
        "Chocolate" to 0.75,
        "Galleta" to 0.5,
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.padding(16.dp))
        Text(
            text = "Maquina Expendedora",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Text("Saldo: $ ${"%.2f".format(balance)}")

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            listOf(0.25, 0.5, 1.0).forEach { a ->
                Button(
                    onClick = {
                        balance += a
                        message = "Saldo actualizado"
                    }
                ) {
                    Text("+$ ${a}")
                }
            }
        }

        Column(
            modifier = Modifier.weight(1f)
        ) {
            products.forEach { product ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("${product.first} - ${product.second}")

                    Button(
                        onClick = {
                            if(balance >= product.second){
                                balance -= product.second
                                message = "Compraste ${product.first}"
                            }else{
                                message = "Saldo insuficiente"
                            }
                        }
                    ) {
                        Text("Comprar")
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            contentAlignment = Alignment.Center
        ){
            Text(message)
        }
    }
}