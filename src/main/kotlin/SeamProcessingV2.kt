package com.youyou

import com.youyou.model.NodeData
import com.youyou.model.Pixel
import com.youyou.model.Seam

// add prevGraph: Map<Pixel, NodeData> as parameter to use as memorization path
fun generateGraphFindMinSeam(listEnergies: List<List<Double>>): Seam {
    val graphNodes = imageGridToGraph(listEnergies)
//    println("**** Graph Last Line Energies ****")
//    println(graphNodes.last().map { it.energy })
    return backTrackGraph(graphNodes)
}

fun backTrackGraph(graphNodes: List<List<NodeData>>): Seam {
    val minLastNode = lastRowMinEnergy(graphNodes)
    var current = minLastNode
    val path = mutableListOf(current)

    while (graphNodes[current.line][current.col].parent != null) {
        current = graphNodes[current.line][current.col].parent!!
        path.add(current)
    }
    return Seam(graphNodes[minLastNode.line][minLastNode.col].energy, path.reversed())
}

fun lastRowMinEnergy(graphNodes: List<List<NodeData>>): Pixel {
    var pixel = Pixel(graphNodes.lastIndex, 0)
    var minEnergy = Double.MAX_VALUE
    for (nodeIndex in graphNodes.last().indices) {
        if (graphNodes.last()[nodeIndex].energy < minEnergy ) {
            minEnergy = graphNodes.last()[nodeIndex].energy
            pixel = pixel.copy(col = nodeIndex)
        }
    }
    return pixel
}

fun imageGridToGraph(listEnergies: List<List<Double>>): List<List<NodeData>> {
    val graphNodes = List(listEnergies.size) { MutableList(listEnergies[0].size) {NodeData(0.0)} }
    listEnergies[0].forEachIndexed { index, energy -> graphNodes[0][index] = NodeData(energy) }
    listEnergies.forEachIndexed { line, doubleList ->
        if(line != 0) {
            doubleList.forEachIndexed { col, _ ->
                graphNodes[line][col] = minAdjacentNode(Pixel(line, col), listEnergies)
            }
        }
    }
    return graphNodes
}

fun minAdjacentNode(currentPixel: Pixel, listEnergies: List<List<Double>>): NodeData {
    val possiblePrevPixels = listOf(
        Pixel(currentPixel.line - 1, currentPixel.col - 1),
        Pixel(currentPixel.line - 1, currentPixel.col),
        Pixel(currentPixel.line - 1, currentPixel.col + 1)
    ).filter { it.line in listEnergies.indices && it.col in listEnergies[0].indices }
    val bestPreviousNode = possiblePrevPixels.map {
        it to listEnergies[currentPixel.line][currentPixel.col] + listEnergies[it.line][it.col]
    }.minBy { it.second }
    return NodeData(bestPreviousNode.second, bestPreviousNode.first)
}