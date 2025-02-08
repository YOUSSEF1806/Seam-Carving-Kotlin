package com.youyou

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

// Program params launch example: -in sky.png -out sky_energy.png
fun main(args: Array<String>) {
    val fileIn = args.indexOf("-in").let { args[it + 1] }
    val fileOut = args.indexOf("-out").let { args[it + 1] }

    val bufferedImage = ImageIO.read(File(fileIn))

//    lowestVerticalSeam(bufferedImage)
    lowestHorizontalSeam(bufferedImage)

    ImageIO.write(bufferedImage, "png", File(fileOut))
}

private fun lowestVerticalSeam(bufferedImage: BufferedImage) {
    val listEnergies = listEnergies(bufferedImage)

    val lowestVSeam = processAndFindMinSeam(listEnergies)
    lowestVSeam.path.forEach {
        bufferedImage.setRGB(it.col, it.line, Color.RED.rgb)
    }
}
private fun lowestHorizontalSeam(bufferedImage: BufferedImage) {
    val listEnergies = listEnergiesHorizontal(bufferedImage)

    val lowestHSeam = processAndFindMinSeam(listEnergies)
    lowestHSeam.path.forEach {
        bufferedImage.setRGB(it.line, it.col, Color.RED.rgb)
    }
}



