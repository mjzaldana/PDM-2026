package com.example.assistance

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import com.example.assistance.ui.voice.VoiceScreen
import com.example.assistance.viewModel.VoiceViewModel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ElegantDashboardScreen()
        }
    }
}
@Composable
fun ElegantDashboardScreen() {
    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF0B0F1A), Color(0xFF1A1F2E), Color(0xFF232946))
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient)
            .padding(16.dp)
    ) {
        HeaderSection()
        Spacer(modifier = Modifier.height(20.dp))
        BalanceCard()
        Spacer(modifier = Modifier.height(20.dp))
        StatsGrid()
        Spacer(modifier = Modifier.height(20.dp))
        ActivitySection()
        Spacer(modifier = Modifier.height(20.dp))
        BottomActionSection()
    }
}


@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text("Dashboard", color = Color.White.copy(alpha = 0.6f))
            Text("Overview", color = Color.White, fontSize = 26.sp, fontWeight = FontWeight.Bold)
        }

        IconButton(onClick = {}) {
            Icon(Icons.Default.Notifications, contentDescription = null, tint = Color.White)
        }
    }
}

@Composable
fun BalanceCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .clip(RoundedCornerShape(28.dp))
            .background(
                Brush.horizontalGradient(
                    listOf(Color(0xFF7F00FF), Color(0xFFE100FF))
                )
            )
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Total Balance", color = Color.White.copy(alpha = 0.8f))

            Text(
                "$24,580.00",
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(
                    onClick = {},
                ) {
                    Text("Add Funds", color = Color.White)
                }

                Button(onClick = {}) {
                    Text("Withdraw", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun StatsGrid() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            StatCard("Revenue", "$12K", Icons.Default.CheckCircle, Modifier.weight(1f))
            StatCard("Users", "1.2K", Icons.Default.Person, Modifier.weight(1f))
        }
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            StatCard("Growth", "+18%", Icons.Default.CheckCircle, Modifier.weight(1f))
            StatCard("Orders", "320", Icons.Default.CheckCircle, Modifier.weight(1f))
        }
    }
}

@Composable
fun StatCard(title: String, value: String, icon: androidx.compose.ui.graphics.vector.ImageVector, modifier: Modifier) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White.copy(alpha = 0.08f))
            .padding(16.dp)
    ) {
        Icon(icon, contentDescription = null, tint = Color.White.copy(alpha = 0.7f))
        Spacer(modifier = Modifier.height(10.dp))
        Text(title, color = Color.White.copy(alpha = 0.6f))
        Spacer(modifier = Modifier.height(6.dp))
        Text(value, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun ActivitySection() {
    Column {
        Text("Recent Activity", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(12.dp))

        repeat(3) {
            ActivityItem()
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
fun ActivityItem() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White.copy(alpha = 0.06f))
            .padding(14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text("Payment Received", color = Color.White)
            Text("Today, 12:45 PM", color = Color.White.copy(alpha = 0.5f), fontSize = 12.sp)
        }

        Text("+$240", color = Color(0xFF00E676), fontWeight = FontWeight.Bold)
    }
}

@Composable
fun BottomActionSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Button(
            onClick = {},
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(18.dp)
        ) {
            Text("Send")
        }

        Button(
            onClick = {},
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(18.dp)
        ) {
            Text("Request")
        }
    }
}

@Composable
fun TransactionDetailScreen() {
    val gradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF0B0F1A), Color(0xFF1A1F2E), Color(0xFF232946))
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
            .padding(16.dp)
    ) {
        TopBar()
        Spacer(modifier = Modifier.height(20.dp))
        StatusSection()
        Spacer(modifier = Modifier.height(20.dp))
        DetailCard()
        Spacer(modifier = Modifier.height(20.dp))
        InfoList()
        Spacer(modifier = Modifier.weight(1f))
        ActionButtons()
    }
}

@Composable
fun TopBar() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {}) {
            Icon(Icons.Default.ArrowBack, contentDescription = null, tint = Color.White)
        }
        Text("Transaction", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun StatusSection() {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
                .background(Color(0xFF00E676).copy(alpha = 0.2f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Color(0xFF00E676), modifier = Modifier.size(40.dp))
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text("Payment Successful", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Text("Completed on Apr 23, 2026", color = Color.White.copy(alpha = 0.6f))
    }
}

@Composable
fun DetailCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(Color.White.copy(alpha = 0.08f))
            .padding(20.dp)
    ) {
        Text("Amount", color = Color.White.copy(alpha = 0.6f))
        Spacer(modifier = Modifier.height(6.dp))
        Text("$240.00", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        Divider(color = Color.White.copy(alpha = 0.1f))

        Spacer(modifier = Modifier.height(16.dp))

        Text("To", color = Color.White.copy(alpha = 0.6f))
        Text("Netflix Subscription", color = Color.White, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun InfoList() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        InfoRow("Transaction ID", "#A123456789")
        InfoRow("Payment Method", "Visa **** 4242")
        InfoRow("Date", "Apr 23, 2026")
        InfoRow("Status", "Completed")
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(Color.White.copy(alpha = 0.05f))
            .padding(14.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, color = Color.White.copy(alpha = 0.6f))
        Text(value, color = Color.White, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun ActionButtons() {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp)
        ) {
            Text("Download Receipt")
        }

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp)
        ) {
            Text("Share")
        }
    }
}

@Preview
@Composable
fun transactionPreview(){
    TransactionDetailScreen()
}





