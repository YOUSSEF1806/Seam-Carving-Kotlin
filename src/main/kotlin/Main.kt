package com.youyou

import java.io.File
import javax.imageio.ImageIO
import kotlin.system.measureTimeMillis
import kotlin.time.DurationUnit
import kotlin.time.toDuration

// Program params launch example: -in <input_file> -out <out_file> -width <nb_v_seam> -height <nb_h_seam>
fun main(args: Array<String>) {
    AppParams.validateParams(args)

    var bufferedImage = ImageIO.read(File(AppParams.fileIn))

    val paramsInRange = AppParams.widthReduc in 1 until bufferedImage.width &&
            AppParams.heightReduc in 1 until bufferedImage.height
    if (paramsInRange) {
        val vProcessTime = measureTimeMillis {
            bufferedImage = dropNbVSeams(bufferedImage, AppParams.widthReduc)
        }
        println("Vertical process Time: ${vProcessTime.toDuration(DurationUnit.MILLISECONDS)}")

        val hProcessTime = measureTimeMillis {
            bufferedImage = dropNbHSeams(bufferedImage, AppParams.heightReduc)
        }
        println("Horizontal process Time: ${hProcessTime.toDuration(DurationUnit.MILLISECONDS)}")

        ImageIO.write(bufferedImage, "png", File(AppParams.fileOut))
    }
    else {
        println("Error: parameters for -width and -height must be between 1 and <imagesize> - 1")
    }
}



