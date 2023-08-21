package model.analizador.lexico.transiciones.s1

/**
 * Object que contiene la verificación de la transición del estado S1 a S2
 */
object S1S2 {
    /**
     * Object para verificar la transición de los estados S1 a S2
     */
    @JvmStatic
    fun isTransicionS1S2(char: Char):Boolean {
        return when (char) {
            '.' -> true
            ',' -> true
            ';' -> true
            ':' -> true
            '(' -> true
            ')' -> true
            '[' -> true
            ']' -> true
            '{' -> true
            '}' -> true
            else -> false
        }
    }
}