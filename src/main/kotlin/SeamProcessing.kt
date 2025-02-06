package com.youyou

import com.youyou.model.NodeData
import com.youyou.model.Pixel
import com.youyou.model.Seam

fun processAndFindMinSeam(listEnergies: List<List<Double>>): Seam {
    val graphNodes = createGraphNodes(listEnergies)
    return getMinSeam(graphNodes)
}

fun getMinSeam(graphNodes: List<List<NodeData>>): Seam {
    var (minSeamEnergy, current) = getMinSeamEnergyAndStart(graphNodes)
    val path = mutableListOf(current)

    while (graphNodes[current.line][current.col].next != null) {
        current = graphNodes[current.line][current.col].next!!
        path.add(current)
    }
    return Seam(minSeamEnergy, path)
}

fun getMinSeamEnergyAndStart(graphNodes: List<List<NodeData>>): Pair<Double, Pixel> {
    val minSeam = graphNodes.first().minBy { it.energy }
    val index = graphNodes.first().indexOf(minSeam)
    return Pair(minSeam.energy, Pixel(0, index))
}

fun createGraphNodes(listEnergies: List<List<Double>>): List<MutableList<NodeData>> {
    val graphNodes = List(listEnergies.size) { MutableList(listEnergies[0].size) {NodeData(0.0)} }
    listEnergies.last().forEachIndexed { col, energy -> graphNodes.last()[col] = NodeData(energy) }
    for (line in listEnergies.lastIndex - 1 downTo 0) {
        listEnergies[line].forEachIndexed { col, _ ->
            graphNodes[line][col] = bestNextPixelFrom(Pixel(line, col), listEnergies, graphNodes)
        }
    }
    return graphNodes
}

fun bestNextPixelFrom(
    current: Pixel,
    listEnergies: List<List<Double>>,
    graphNodes: List<List<NodeData>>,
): NodeData {
    val nextPixels = possibleNextPixel(current, listEnergies)
    val bestPreviousNode = nextPixels.map {
        it to graphNodes[it.line][it.col].energy + listEnergies[current.line][current.col]
    }.minBy { it.second }
    return NodeData(bestPreviousNode.second, bestPreviousNode.first)
}

fun possibleNextPixel(current: Pixel, listEnergies: List<List<Double>>): List<Pixel> {
    val nbLines = listEnergies.size
    val nbCols = listEnergies.first().size
    return listOf(
        Pixel(current.line + 1, current.col - 1),
        Pixel(current.line + 1, current.col),
        Pixel(current.line + 1, current.col + 1)
    ).filter { it.line in 0 until nbLines && it.col in 0 until nbCols}
}