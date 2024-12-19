package com.youyou

import java.awt.Color
import java.awt.image.BufferedImage
import kotlin.math.pow
import kotlin.math.sqrt

fun toIntensityBufferedImage(
    listEnergies: List<List<Double>>,
    bufferedImage: BufferedImage,
) {
    val maxEnergyValue = listEnergies.flatten().max()
    listEnergies.forEachIndexed { x, energies ->
        energies.forEachIndexed { y, energy ->
            val intensity = (255.0 * energy / maxEnergyValue).toInt()
            bufferedImage.setRGB(x, y, Color(intensity, intensity, intensity).rgb)
        }
    }
}

fun listEnergies(bufferedImage: BufferedImage) = (0 until bufferedImage.width).map { x ->
    (0 until bufferedImage.height).map { y ->
        energyPixel(x, y, bufferedImage)
    }
}

fun energyPixel(x: Int, y: Int, bufferedImage: BufferedImage): Double {
    val squareXGradient = squareXGradient(x, y, bufferedImage)
    val squareYGradient = squareYGradient(x, y, bufferedImage)

    return sqrt(squareXGradient + squareYGradient)
}

fun squareYGradient(x: Int, y: Int, bufferedImage: BufferedImage): Double {
    if (y == 0)
        return squareYGradient(x, y + 1, bufferedImage)
    if (y == bufferedImage.height - 1)
        return squareYGradient(x, y - 1, bufferedImage)

    val colorNextY = Color(bufferedImage.getRGB(x, y + 1))
    val colorPreviousY = Color(bufferedImage.getRGB(x, y - 1))

    return listOf(
        (colorPreviousY.red - colorNextY.red).toDouble(),
        (colorPreviousY.green - colorNextY.green).toDouble(),
        (colorPreviousY.blue - colorNextY.blue).toDouble()
    ).sumOf { it.pow(2) }
}

fun squareXGradient(x: Int, y: Int, bufferedImage: BufferedImage): Double {
    if (x == 0)
        return squareXGradient(1, y, bufferedImage)
    if (x == bufferedImage.width - 1)
        return squareXGradient(x - 1, y, bufferedImage)

    val colorNextX = Color(bufferedImage.getRGB(x + 1, y))
    val colorPreviousX = Color(bufferedImage.getRGB(x - 1, y))

    return listOf(
        (colorPreviousX.red - colorNextX.red).toDouble(),
        (colorPreviousX.green - colorNextX.green).toDouble(),
        (colorPreviousX.blue - colorNextX.blue).toDouble()
    ).sumOf { it.pow(2) }
}
