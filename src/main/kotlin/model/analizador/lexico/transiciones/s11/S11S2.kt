package model.analizador.lexico.transiciones.s11

/**
 * Object para verificar que caracteres no pertenecientes al espacio en blanco delimitan una cadena.
 */
object S11S2 {


    fun isdelimitadorDeIentificador(char: Char): Boolean {
        return when(char) {
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