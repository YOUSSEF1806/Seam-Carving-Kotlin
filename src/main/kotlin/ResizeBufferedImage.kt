package com.youyou

import com.youyou.model.Pixel
import java.awt.image.BufferedImage


private fun BufferedImage.removeVSeam(path: List<Pixel>): BufferedImage {
    val width = this.width
    val height = this.height

    val newImage = BufferedImage(width - 1, height, this.type)

    for (line in 0 until height) {
        for (col in 0 until width - 1) {
            val oldCol = if (col < path[line].col) col else col + 1
            newImage.setRGB(col, line, this.getRGB(oldCol, line))
        }
    }

    return newImage
}

private fun BufferedImage.removeHSeam(path: List<Pixel>): BufferedImage {
    val width = this.width
    val height = this.height

    val newImage = BufferedImage(width, height - 1, this.type)

    for (col in 0 until width) {
        for (line in 0 until height - 1) {
            val oldLine = if (line < path[col].line) line else line + 1
            newImage.setRGB(col, line, this.getRGB(col, oldLine))
        }
    }

    return newImage
}

fun dropNbVSeams(bufferedImage: BufferedImage, nbVDelete: Int): BufferedImage {
    var buffer = bufferedImage

    repeat(nbVDelete) {
        val listEnergies = listEnergies(buffer)
        val lowestVSeam = processAndFindMinSeam(listEnergies)
        buffer = buffer.removeVSeam(lowestVSeam.path)
    }

    return buffer
}

fun dropNbHSeams(bufferedImage: BufferedImage, nbHDelete: Int): BufferedImage {
    var buffer = bufferedImage

    repeat(nbHDelete) {
        val listEnergies = listEnergiesHorizontal(buffer)
        val lowestHSeam = processAndFindMinSeam(listEnergies)
        buffer = buffer.removeHSeam(lowestHSeam.path.map { Pixel(it.col, it.line) })
    }

    return buffer
}
