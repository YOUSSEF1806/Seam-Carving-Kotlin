package com.youyou

import java.awt.Color
import java.awt.image.BufferedImage
import kotlin.math.pow
import kotlin.math.sqrt

fun toIntensityBufferedImage(
    listEnergies: List<List<Double>>,
    bufferedImage: BufferedImage
) {
    val maxEnergyValue = listEnergies.flatten().max()
    listEnergies.forEachIndexed { line, energies ->
        energies.forEachIndexed { col, energy ->
            val intensity = (255.0 * energy / maxEnergyValue).toInt()
            bufferedImage.setRGB(col, line, Color(intensity, intensity, intensity).rgb)
        }
    }
}

fun listEnergies(bufferedImage: BufferedImage) = (0 until bufferedImage.height).map { line ->
    (0 until bufferedImage.width).map { col ->
        energyPixel(col, line, bufferedImage)
    }
}

fun listEnergiesHorizontal(bufferedImage: BufferedImage): List<List<Double>> {
    val listEnergiesV = listEnergies(bufferedImage)
    val listEnergiesH = List(listEnergiesV.first().size) { MutableList(listEnergiesV.size) { 0.0 } }
    listEnergiesV.forEachIndexed { line, energies ->
        energies.forEachIndexed { col, energy ->
            listEnergiesH[col][line] = energy
        }
    }
    return listEnergiesH
}

fun energyPixel(col: Int, line: Int, bufferedImage: BufferedImage): Double {
    val squareXGradient = squareXGradient(col, line, bufferedImage)
    val squareYGradient = squareYGradient(col, line, bufferedImage)

    return sqrt(squareXGradient + squareYGradient)
}

private fun squareYGradient(col: Int, line: Int, bufferedImage: BufferedImage): Double {
    if (line == 0)
        return squareYGradient(col, 1, bufferedImage)
    if (line == bufferedImage.height - 1)
        return squareYGradient(col, line - 1, bufferedImage)

    val colorNextY = Color(bufferedImage.getRGB(col, line + 1))
    val colorPreviousY = Color(bufferedImage.getRGB(col, line - 1))

    return squareGradient(colorPreviousY, colorNextY)
}

private fun squareXGradient(col: Int, line: Int, bufferedImage: BufferedImage): Double {
    if (col == 0)
        return squareXGradient(1, line, bufferedImage)
    if (col == bufferedImage.width - 1)
        return squareXGradient(col - 1, line, bufferedImage)

    val colorNextX = Color(bufferedImage.getRGB(col + 1, line))
    val colorPreviousX = Color(bufferedImage.getRGB(col - 1, line))

    return squareGradient(colorPreviousX, colorNextX)
}

private fun squareGradient(colorPreviousX: Color, colorNextX: Color) = listOf(
    (colorPreviousX.red - colorNextX.red).toDouble(),
    (colorPreviousX.green - colorNextX.green).toDouble(),
    (colorPreviousX.blue - colorNextX.blue).toDouble()
).sumOf { it.pow(2) }
