package com.example.notes.ui.animation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import com.example.notes.data.VisualNode
import kotlinx.coroutines.launch
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notes.viewModel.GraphViewModel

@Composable
fun DraggableNodeLabel(node: VisualNode) {
    val scope = rememberCoroutineScope()
    Box(
        modifier = Modifier
            .offset { IntOffset(node.offset.value.x.toInt() - 20, node.offset.value.y.toInt() - 40) }
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    scope.launch {
                        node.offset.snapTo(node.offset.value + dragAmount)
                    }
                }
            }
    ) {
        Text(
            text = node.title,
            color = Color.White,
            fontSize = 11.sp,
            modifier = Modifier
                .background(Color.Black.copy(alpha = 0.7f), RoundedCornerShape(4.dp))
                .padding(horizontal = 6.dp, vertical = 2.dp)
        )
    }
}

@Composable
fun GraphVisualizer(viewModel: GraphViewModel) {
    Canvas(modifier = Modifier.fillMaxSize().clipToBounds()) {
        viewModel.edges.forEach { edge ->
            val start = viewModel.nodes.find { it.id == edge.sourceId }?.offset?.value
            val end = viewModel.nodes.find { it.id == edge.targetId }?.offset?.value

            if (start != null && end != null) {
                drawLine(
                    brush = Brush.linearGradient(
                        colors = listOf(Color(0xFF00E5FF), Color(0xFF7000FF))
                    ),
                    start = start,
                    end = end,
                    strokeWidth = 2f,
                    alpha = 0.6f
                )
            }
        }

        viewModel.nodes.forEach { node ->
            val pos = node.offset.value
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(node.color.copy(alpha = 0.5f), Color.Transparent),
                    center = pos,
                    radius = 40f
                ),
                center = pos,
                radius = 40f
            )
            drawCircle(
                color = node.color,
                radius = 8f,
                center = pos
            )
        }
    }
}