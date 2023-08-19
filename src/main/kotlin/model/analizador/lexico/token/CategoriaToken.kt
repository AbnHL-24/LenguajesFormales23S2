package model.analizador.lexico.token

/**
 * Esta enumeración contiene las categories de los tokens, asi como el color correspondiente
 * @param categoria és la categoria del token.
 * @param color és el color que identifica a ésta categoria.
 */
enum class CategoriaToken(private val categoria: String, private val color: String) {
    IDENTIFICADORES("Identificador", "Negro/Blanco"),
    OPERADORES_ARITMETICOS("Operador aritmético", "Celeste"),
    COMPARACION("Comparación", "Celeste"),
    LOGICOS("Lógico", "Celeste"),
    ASIGNACION("Asignación", "Celeste"),
    PALABRAS_RESERVADAS("Palabra Clave", "Morado"),
    CONSTANTES("Constante", "Rojo/anaranjado"),
    COMENTARIOS("Comentarios", "Gris"),
    OTROS("Otros", "Verde")
}