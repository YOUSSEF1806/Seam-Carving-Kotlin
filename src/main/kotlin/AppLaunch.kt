package com.youyou


object AppParams {
    lateinit var fileIn: String
    lateinit var fileOut: String
    var widthReduc: Int = 0
    var heightReduc: Int = 0


    fun validateParams(args: Array<String>): Boolean {
        val nbParams = args.size / 2
        var validParams = 0

        if (nbParams != 4) {
            throw NbArgsException()
        }
        val mapParams = args.toList().chunked(2) { it.first() to it.last() }
        mapParams.forEach {
            when (it.first) {
                "-in" -> {
                    fileIn = it.second
                    validParams++
                }
                "-out" -> {
                    fileOut = it.second
                    validParams++
                }
                "-width" -> {
                    widthReduc = it.second.toInt()
                    validParams++
                }
                "-height" -> {
                    heightReduc = it.second.toInt()
                    validParams++
                }
            }
        }
        if (validParams != 4) throw NbArgsException()

        return true
    }
}

class NbArgsException : Exception() {
    override val message: String
        get() = "nb arguments or syntax invalid, Follow the format: \n" +
                "-in <input_file> -out <out_file> -width <nb_v_seam> -height <nb_h_seam>\n" +
                "<nb_v_seam>: is the number of vertical seam to remove\n" +
                "<nb_h_seam>: is the number of horizontal seam to remove\n"
}
