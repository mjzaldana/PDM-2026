package com.example.content_layout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.content_layout.ui.theme.Content_layoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Content_layoutTheme {

            }
        }
    }
}

@Preview
@Composable
fun layout(){
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Column(
            modifier = Modifier.fillMaxSize()
        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(Color.Gray)
            ){
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color.Green)
                        .align(Alignment.CenterStart)
                        .padding(start = 8.dp)
                )
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color.Green)
                        .align(Alignment.Center)
                )
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterEnd),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ){
                    repeat(2){
                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .background(Color.Blue)
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(Color.Red)
            ){
                Column(
                    modifier = Modifier
                        .weight(0.4f)
                        .fillMaxHeight()
                        .background(Color.Magenta),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    repeat(4){ index ->
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .background(Color.Cyan)
                        ){
                            if(index == 1){
                                Box(
                                    modifier = Modifier
                                        .size(20.dp)
                                        .background(Color.Red)
                                        .align(Alignment.TopEnd)
                                )
                            }
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .weight(0.6f)
                        .fillMaxHeight()
                        .background(Color.Yellow),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    repeat(2){
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            repeat(2){
                                Box(
                                    modifier = Modifier
                                        .size(80.dp)
                                        .background(Color.White)
                                )
                            }
                        }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(Color.Black)
            )
        }
        Box(
            modifier = Modifier
                .size(200.dp)
                .background(Color.Green)
                .align(Alignment.Center)
        )
    }
}
