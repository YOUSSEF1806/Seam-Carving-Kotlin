package com.youyou

import com.youyou.model.NodeData
import com.youyou.model.Pixel
import com.youyou.model.Seam
import java.util.*

// add prevGraph: Map<Pixel, NodeData> as parameter to use as memorization path
fun dijkstraEnergyPath(listEnergies: List<List<Double>>, source: Pixel): Map<Pixel, NodeData> {
    val graph = mutableMapOf<Pixel, NodeData>()
    val queue: PriorityQueue<Pair<Double, Pixel>> = PriorityQueue(compareBy { it.first })

    queue.add(Pair(listEnergies[source.line][source.col],source))
    while(queue.isNotEmpty()) {
        val (currentEnergy, current) = queue.poll()
        val adjacentPixels = nextAdjacentPixels(current.line, current.col)
            .filter { it.line in listEnergies.indices && it.col in listEnergies[0].indices}
        for (p in adjacentPixels) {
            val pDistance = currentEnergy + listEnergies[p.line][p.col]
            if(pDistance < (graph.getOrElse(p) { NodeData() }.energy)) {
                graph[p] = NodeData(pDistance, current)
                queue.add(Pair(pDistance, p))
            }
        }
    }
    return graph.toMap()
}

fun backTrackGraph(graph: Map<Pixel, NodeData>, endIndex: Int): Seam {
    val lastNode = graph.filterKeys { it.line == endIndex }.minBy { it.value.energy }
    var current = lastNode.key
    val path = mutableListOf(current)

    while (graph[current]?.parent != null) {
        current = graph[current]?.parent!!
        path.add(current)
    }
    return Seam(lastNode.value.energy, path.reversed())
}

fun nextAdjacentPixels(line: Int, col: Int) = listOf(
    Pixel(line + 1, col - 1),
    Pixel(line + 1, col),
    Pixel(line + 1, col + 1)
)