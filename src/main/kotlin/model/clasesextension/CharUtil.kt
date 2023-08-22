package model.clasesextension

class CharUtil {
    companion object {
        fun Char.Companion.isDigitNotZero(char: Char): Boolean {
            return if (char.isDigit()) {
                char.digitToInt() in 1..9
            } else false
        }
    }
}