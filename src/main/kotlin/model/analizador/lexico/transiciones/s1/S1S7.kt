package model.analizador.lexico.transiciones.s1

/**
 * Object para verificar las transiciones de S1 a S7
 */
object S1S7 {

    fun isTransicionS1S7(char: Char): Boolean {
        return when(char) {
            '+' -> true
            '%' -> true
            '>' -> true
            '<' -> true
            '=' -> true
            else -> false
        }
    }
}