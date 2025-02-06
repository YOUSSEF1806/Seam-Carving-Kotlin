package com.youyou.model

data class Pixel(val line: Int, val col: Int)

data class NodeData(
    val energy: Double = Double.MAX_VALUE,
    val next: Pixel? = null
)

data class Seam(val energy: Double, val path: List<Pixel>)
