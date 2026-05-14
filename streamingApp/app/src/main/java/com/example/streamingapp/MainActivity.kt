package com.example.streamingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.streamingapp.data.Show
import com.example.streamingapp.ui.theme.StreamingAppTheme
import com.example.streamingapp.viewModel.ShowViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StreamingAppTheme {
                TvMazeApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TvMazeApp(viewModel: ShowViewModel = viewModel()) {
    AnimatedContent(
        targetState = viewModel.selectedShow,
        label = "screen_transition"
    ) { targetShow ->
        if (targetShow == null) {
            Scaffold(
                topBar = {
                    Column(modifier = Modifier.background(Color.Black)) {
                        Text(
                            "TV MAZE STREAMING",
                            color = Color.Red,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(16.dp).fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleLarge
                        )
                        val genres = listOf("Action", "Drama", "Comedy", "Horror", "Sci-Fi")
                        LazyRow(
                            modifier = Modifier.padding(bottom = 8.dp),
                            contentPadding = PaddingValues(horizontal = 16.dp)
                        ) {
                            items(genres) { genre ->
                                FilterChip(
                                    selected = false,
                                    onClick = { viewModel.loadShows(genre) },
                                    label = { Text(genre, color = Color.White) },
                                    modifier = Modifier.padding(end = 8.dp),
                                    colors = FilterChipDefaults.filterChipColors(containerColor = Color.DarkGray)
                                )
                            }
                        }
                    }
                },
                containerColor = Color.Black
            ) { padding ->
                if (viewModel.isLoading) {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = Color.Red)
                    }
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = padding,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        items(viewModel.shows) { show ->
                            Box(modifier = Modifier.clickable { viewModel.selectShow(show) }) {
                                ShowItem(show)
                            }
                        }
                    }
                }
            }
        } else {
            DetailScreen(show = targetShow, onBack = { viewModel.selectShow(null) })
        }
    }
}

@Composable
fun ShowItem(show: Show) {
    Column(
        modifier = Modifier.padding(8.dp).fillMaxWidth()
    ) {
        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.height(250.dp).fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            AsyncImage(
                model = show.image?.medium,
                contentDescription = show.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
                placeholder = ColorPainter(Color.DarkGray)
            )
        }
        Text(
            text = show.name,
            color = Color.White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 8.dp),
            style = MaterialTheme.typography.titleMedium
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = " ${show.rating?.average ?: "N/A"}",
                color = Color.LightGray,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Composable
fun DetailScreen(show: Show, onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(rememberScrollState())
    ) {
        Box {
            AsyncImage(
                model = show.image?.medium,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth().height(400.dp)
            )
            Box(
                modifier = Modifier.fillMaxSize().height(400.dp)
                    .background(Brush.verticalGradient(listOf(Color.Transparent, Color.Black)))
            )

            IconButton(
                onClick = onBack,
                modifier = Modifier.padding(top = 48.dp, start = 16.dp).background(Color.Black.copy(0.5f),
                    CircleShape
                )
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
            }
        }

        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = show.name,
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            Row(modifier = Modifier.padding(vertical = 12.dp)) {
                show.genres?.forEach { genre ->
                    Surface(
                        color = Color.Red.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text(genre, color = Color.Red, modifier = Modifier.padding(4.dp), style = MaterialTheme.typography.labelSmall)
                    }
                }
            }

            val cleanSummary = show.summary?.replace(Regex("<[^>]*>"), "") ?: "No description available."
            Text(
                text = cleanSummary,
                color = Color.LightGray,
                style = MaterialTheme.typography.bodyMedium,
                lineHeight = 24.sp
            )
        }
    }
}
