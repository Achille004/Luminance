package Luminanza

import java.io.File
import kotlin.test.Test
import kotlin.test.assertTrue

class LuminanceTest {
    @Test
    fun expectTrue() {
        val classUnderTest = App()
        assertTrue(classUnderTest.translate(File("in.png"), File("out.txt")))
    }
}
