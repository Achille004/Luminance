/*
    Luminance
    Copyright (C) 2022  Francesco Marras

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/

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
