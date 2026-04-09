package com.example.new_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.new_app.ui.theme.New_appTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

        }
    }
}

@Composable
fun DashboardScreen(){
    val backGroundGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF9933ff),
            Color(0xFF6666ff),
        )
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(backGroundGradient)
    ){
        Column(
            modifier = Modifier.fillMaxSize().padding(20.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column{
                    Text(
                        "Welcome Back",
                        color = Color.White.copy(alpha = 0.7f),
                        fontSize = 14.sp
                    )
                    Text(
                        "Mauricio",
                        color = Color.White,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        "M",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color.White.copy(alpha = 0.08f))
                    .border(
                        1.dp,
                        Color.White,
                        RoundedCornerShape(24.dp)
                    )
                    .padding(20.dp)
            ){
                Column{
                    Text(
                        "Total Balance",
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        "$10,000",
                        color = Color.White,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        BalanceItem("expense", "+$20.00")
                        BalanceItem("income", "-$10.00")
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ActionButton("Send")
                ActionButton("Pay")
                ActionButton("Top Up")
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                "Recent Transactions",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(12.dp))

            Column{
                TransactionItem("Davivienda", "-$15.00", "Today")
                TransactionItem("Pago", "+$15.00", "yesteday")
                TransactionItem("Renta", "-$15.00", "yesterday")
            }
        }
    }
}

@Composable
fun BalanceItem(title: String, amount: String){
    Column{
        Text(
            title,
            color = Color.White.copy(alpha = 0.6f),
            fontSize = 12.sp
        )
        Text(
            amount,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ActionButton(text: String){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.1f))
                .border(
                    width = 1.dp,
                    Color.White.copy(alpha = 0.2f),
                    CircleShape
                )
                .clickable{},
            contentAlignment = Alignment.Center
        ){
            Text(
                text.first().toString(),
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text, color = Color.White, fontSize = 12.sp
        )
    }
}

@Composable
fun TransactionItem(title: String, amount: String, date: String){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White.copy(alpha = 0.05f))
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Column {
            Text(title, color = Color.White)
            Text(date, color = Color.White.copy(alpha = 0.5f), fontSize = 12.sp)
        }

        Text(
            amount,
            color = if(amount.contains("+")) Color(0xFF66ff33) else Color(0xFFff3300),
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
@Preview
fun GeneralPreview(){
    DashboardScreen()
}

