package Luminanza

import com.google.common.io.Files
import java.awt.Color
import java.io.File
import java.io.PrintWriter
import javax.imageio.ImageIO

class App {

    val chars =
            " .'`^\",:;Il!i><~+_-?][}{1)(|\\/tfjrxnuvczXYUJCLQ0OZmwqpdbkhao*#MW&8%B@$" // 70 chars

    public fun translate(inputFile: File, outputFile: File): Boolean {
        inputFile.mkdirs()

        if (!(inputFile.isFile() && Files.getFileExtension(inputFile.getName()).equals("png"))) {
            return false
        }

        val pw = PrintWriter(outputFile)
        val img = ImageIO.read(inputFile)

        for (y: Int in 0..(img.getHeight() - 1)) {
            for (x: Int in 0..(img.getWidth() - 1)) {
                // get the color
                var color = Color(img.getRGB(x, y))

                // get luminance from 0 to 255
                var luminance = color.red * 0.2126 + color.green * 0.7152 + color.blue * 0.0722

                // print the result
                pw.print(chars.get((luminance / 255 * 70).toInt()))
                pw.print(" ")
            }
            pw.println()
        }
        pw.flush()
        pw.close()

        return true
    }
}

fun main() {
    App().translate(File("in.png"), File("out.txt"))
}
