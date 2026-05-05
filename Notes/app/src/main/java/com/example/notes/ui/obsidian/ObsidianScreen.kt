package com.example.notes.ui.obsidian

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notes.ui.animation.DraggableNodeLabel
import com.example.notes.ui.animation.GraphVisualizer
import com.example.notes.viewModel.GraphViewModel
@Composable
fun ObsidianApp() {
    val viewModel: GraphViewModel = viewModel()
    var titleInput by remember { mutableStateOf("") }
    var contentInput by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().background(Color(0xFF050505))) {
        Card(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF121212))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                OutlinedTextField(
                    value = titleInput,
                    onValueChange = { titleInput = it },
                    label = { Text("Título de la Nota") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                OutlinedTextField(
                    value = contentInput,
                    onValueChange = { contentInput = it },
                    label = { Text("Escribe aquí... usa [[Título]]") },
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
                )
                Button(
                    onClick = {
                        viewModel.addNote(titleInput, contentInput)
                        titleInput = ""
                        contentInput = ""
                    },
                    modifier = Modifier.align(Alignment.End).padding(top = 12.dp)
                ) {
                    Text("Guardar y Enlazar")
                }
            }
        }

        Box(modifier = Modifier.fillMaxSize().weight(1f)) {
            GraphVisualizer(viewModel)

            viewModel.nodes.forEach { node ->
                key(node.id) {
                    DraggableNodeLabel(node)
                }
            }
        }
    }
}