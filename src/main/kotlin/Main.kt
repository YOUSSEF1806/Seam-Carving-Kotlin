package com.youyou

import java.awt.Color
import java.io.File
import javax.imageio.ImageIO

fun main(args: Array<String>) {
    val fileIn = args.indexOf("-in").let { args[it + 1] }
    val fileOut = args.indexOf("-out").let { args[it + 1] }

    val file = File(fileIn)
    val bufferedImage = ImageIO.read(file)
    bufferedImage.apply {
        (0 until width).forEach { x ->
            (0 until height).forEach { y ->
                this.setRGB(x, y , invertColor(this.getRGB(x, y)))
            }
        }
    }
    ImageIO.write(bufferedImage, "png", File(fileOut))
}

fun invertColor(rgb: Int): Int {
    val color = Color(rgb)
    return Color(255 - color.red, 255 - color.green, 255 - color.blue).rgb
}
