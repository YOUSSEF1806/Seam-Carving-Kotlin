package com.youyou

import java.awt.Color
import java.io.File
import javax.imageio.ImageIO

// Program params launch example: -in sky.png -out sky_energy.png
fun main(args: Array<String>) {
    val fileIn = args.indexOf("-in").let { args[it + 1] }
    val fileOut = args.indexOf("-out").let { args[it + 1] }

    val bufferedImage = ImageIO.read(File(fileIn))

    val listEnergies = listEnergies(bufferedImage)

//    toIntensityBufferedImage(listEnergies, bufferedImage)

    val lowestSeam = generateGraphFindMinSeam(listEnergies)
    lowestSeam.path.forEach {
        bufferedImage.setRGB(it.col, it.line, Color.RED.rgb)
    }

    ImageIO.write(bufferedImage, "png", File(fileOut))
}

