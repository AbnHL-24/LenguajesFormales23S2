package model.analizador.lexico.token

/**
 * Estos son los tipos de token, de manera independiente.
 * @see CategoriaToken para la categoria de cada tipo.
 * @param tipo és el tipo de cada token.
 * @param categoria és la categoria a la qual pertenece el token.
 */
enum class TipoToken(private val tipo:String, private val categoria: CategoriaToken) {
    IDENTIFICADOR("Identificador", CategoriaToken.IDENTIFICADORES),

    SUMA("Suma", CategoriaToken.OPERADORES_ARITMETICOS),
    RESTA("Resta", CategoriaToken.OPERADORES_ARITMETICOS),
    MULTIPLICACION("Multiplicación", CategoriaToken.OPERADORES_ARITMETICOS),
    EXPONENTE("Exponente", CategoriaToken.OPERADORES_ARITMETICOS),
    DIVISION("División", CategoriaToken.OPERADORES_ARITMETICOS),
    MODULO("Modulo", CategoriaToken.OPERADORES_ARITMETICOS),

    IGUAL_A("Igual", CategoriaToken.COMPARACION),
    DIFERENTE("Diferente", CategoriaToken.COMPARACION),
    MAYOR_QUE("Mayor que", CategoriaToken.COMPARACION),
    MENOR_QUE("Menor que", CategoriaToken.COMPARACION),
    MAYOR_O_IGUAL_QUE("Mayor o igual que", CategoriaToken.COMPARACION),
    MENOR_O_IGUAL_QUE("Menor ¡¡o igual que", CategoriaToken.COMPARACION),

    Y("Y", CategoriaToken.LOGICOS),
    O("O", CategoriaToken.LOGICOS),
    NEGACION("Negación", CategoriaToken.LOGICOS),

    ASIGNACION("Asignación", CategoriaToken.ASIGNACION),
    SUMA_Y_ASIGNACION("Suma y asignación", CategoriaToken.ASIGNACION),
    RESTA_Y_ASIGNACION("Resta y asignación", CategoriaToken.ASIGNACION),
    MULTIPLICACION_Y_ASIGNACION("Multiplicación y asignación", CategoriaToken.ASIGNACION),
    DIVISION_Y_ASIGNACION("División y asignación", CategoriaToken.ASIGNACION),
    EXPONENTE_Y_ASIGNACION("Exponente y asignación", CategoriaToken.ASIGNACION),
    MODULO_Y_ASIGNACION("Modulo y asignación", CategoriaToken.ASIGNACION),

    PALABRA_RESERVADA("Palabra reservada", CategoriaToken.PALABRAS_RESERVADAS),

    ENTERO("Entero", CategoriaToken.CONSTANTES),
    DECIMAL("Decimal", CategoriaToken.CONSTANTES),
    CADENA("Cadena", CategoriaToken.CONSTANTES),
    BOOLEANA("Boolena", CategoriaToken.CONSTANTES),

    COMENTARIO("Comentario", CategoriaToken.COMENTARIOS),

    PARENTESIS_APERTURA("Paréntesis apertura", CategoriaToken.OTROS),
    PARENTESIS_CIERRE("Paréntesis cierre", CategoriaToken.OTROS),
    LLAVE_APERTURA("Paréntesis apertura", CategoriaToken.OTROS),
    LLAVE_CIERRE("Paréntesis cierre", CategoriaToken.OTROS),
    CORCHETE_APERTURA("Corchete apertura", CategoriaToken.OTROS),
    CORCHETE_CIERRE("Corchete cierre", CategoriaToken.OTROS),
    PUNTO("Punto", CategoriaToken.OTROS),
    COMA("Coma", CategoriaToken.OTROS),
    PUNTO_Y_COMA("Punto y coma", CategoriaToken.OTROS),
    DOS_PUNTOS("Dos puntos", CategoriaToken.OTROS),

    ERROR("Error", CategoriaToken.ERROR),
    NULL("Null", CategoriaToken.NULL);

    /**
     * Cuerpo necesario para usar los siguientes métodos de manera estática.
     */
    companion object {
        /**
         * Retornar que tipo de token Otros es.
         */
        fun isOtros(char: Char): TipoToken {
            return when(char) {
                '.' -> PUNTO
                ',' -> COMA
                ';' -> PUNTO_Y_COMA
                ':' -> DOS_PUNTOS
                '(' -> PARENTESIS_APERTURA
                ')' -> PARENTESIS_CIERRE
                '[' -> CORCHETE_APERTURA
                ']' -> CORCHETE_CIERRE
                '{' -> LLAVE_APERTURA
                '}' -> LLAVE_CIERRE
                else -> ERROR
            }
        }

        /**
         * Retorna que tipo de token simple es.
         */
        fun isSignosSimples(char: Char): TipoToken {
            return when(char) {
                '+' -> SUMA
                '%' -> MODULO
                '>' -> MAYOR_QUE
                '<' -> MENOR_QUE
                '=' -> ASIGNACION
                else -> ERROR
            }
        }


        fun isSignosCompuestos(tipoToken: TipoToken): TipoToken {
            return when(tipoToken) {
                SUMA -> SUMA_Y_ASIGNACION
                RESTA -> RESTA_Y_ASIGNACION
                MULTIPLICACION -> MULTIPLICACION_Y_ASIGNACION
                DIVISION -> DIVISION_Y_ASIGNACION
                EXPONENTE -> EXPONENTE_Y_ASIGNACION
                MODULO -> MODULO_Y_ASIGNACION
                MAYOR_QUE -> MAYOR_O_IGUAL_QUE
                MENOR_QUE -> MENOR_O_IGUAL_QUE
                ASIGNACION -> IGUAL_A
                else -> ERROR
            }
        }
    }
}