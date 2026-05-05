package com.example.notes.viewModel

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.data.VisualEdge
import com.example.notes.data.VisualNode
import kotlinx.coroutines.launch

class GraphViewModel : ViewModel() {
    val nodes = mutableStateListOf<VisualNode>()
    val edges = mutableStateListOf<VisualEdge>()

    fun addNote(title: String, content: String) {
        val normalizedId = title.trim().lowercase()
        if (normalizedId.isEmpty()) return

        if (nodes.none { it.id == normalizedId }) {
            nodes.add(VisualNode(id = normalizedId, title = title))
        }

        val regex = Regex("\\[\\[(.*?)\\]\\]")
        val matches = regex.findAll(content)

        matches.forEach { match ->
            val targetTitle = match.groupValues[1].trim()
            val targetId = targetTitle.lowercase()

            val targetNode = nodes.find { it.id == targetId }
            if (targetNode != null && targetId != normalizedId) {

                val alreadyExists = edges.any {
                    (it.sourceId == normalizedId && it.targetId == targetId) ||
                            (it.sourceId == targetId && it.targetId == normalizedId)
                }

                if (!alreadyExists) {
                    edges.add(VisualEdge(normalizedId, targetId))
                    applySpringPhysics(normalizedId, targetId)
                }
            }
        }
    }

    private fun applySpringPhysics(sourceId: String, targetId: String) {
        val source = nodes.find { it.id == sourceId }
        val target = nodes.find { it.id == targetId }

        if (source != null && target != null) {
            viewModelScope.launch {
                try {
                    val targetPos = target.offset.value
                    source.offset.animateTo(
                        targetValue = Offset(targetPos.x + 150f, targetPos.y + 150f),
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioLowBouncy,
                            stiffness = Spring.StiffnessVeryLow
                        )
                    )
                } catch (e: Exception) {
                    source.offset.snapTo(target.offset.value + Offset(150f, 150f))
                }
            }
        }
    }
}